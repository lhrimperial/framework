package com.github.framework.server.cache.aspect;

import com.github.framework.server.cache.annotation.RedisCacheKey;
import com.github.framework.server.cache.annotation.RedisCacheable;
import com.github.framework.server.database.redis.RedisClient;
import com.github.framework.util.ReflectionUtils;
import com.github.framework.util.json.FastJsonUtil;
import com.github.framework.util.string.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author： longhairen
 * @Date：2016年7月11日 下午3:53:15
 * @Description：拦截@RedisCacheable注解
 */
@Aspect
public class RedisCacheableAspect {

    private Logger logger = LoggerFactory.getLogger(RedisCacheableAspect.class.getName());

    @Autowired
    private RedisClient redisClient;

    @Around("@annotation(redisCache)")
    public Object cached(final ProceedingJoinPoint pjd, RedisCacheable redisCache) throws Throwable {
        String redisKey = getCacheKey(pjd, redisCache);
        switch (redisCache.option()) {
            case SEL:
                return sel(redisKey, pjd, redisCache);
            case ADD:
                return del(redisKey, pjd);
            case UP:
                return del(redisKey, pjd);
            case DEL:
                return del(redisKey, pjd);
            default:
                break;
        }
        return null;
    }

    /**
     * 查询
     * @param key
     * @param pjp
     * @param redisCache
     * @return
     * @throws Throwable
     */
    private Object sel(String key, ProceedingJoinPoint pjp, RedisCacheable redisCache) throws Throwable {
        try {
            String returnType = getReturnType(pjp);
            Object value = redisClient.get(key);    //从缓存获取数据
            if (value != null) {
                Object result = value;
                try {
                    result = FastJsonUtil.json2Bean((String) value, Class.forName(returnType));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }

            value = pjp.proceed();      //跳过缓存,到后端查询数据

            if (value == null) {
                return value;
            }

            if (redisCache.expire() <= 0) {      //如果没有设置过期时间,则默认缓存24小时
                redisClient.set(key, FastJsonUtil.toJsonString(value));
            } else {                    //否则设置缓存时间
                redisClient.set(key, FastJsonUtil.toJsonString(value), redisCache.expire());
            }
            return value;
        } catch (Exception e) {
            logger.error("RedisCacheableAspect Redis缓存存取异常：" + e.getMessage());
            return pjp.proceed();
        }
    }

    private Object del(String redisKey, ProceedingJoinPoint pjd) {
        logger.info("delete redisKey=" + redisKey);
        redisClient.del(redisKey);
        Object result = null;
        try {
            result = pjd.proceed();
        } catch (Throwable e) {
            logger.error("RedisCacheableAspect pjd.proceed error", e);
        }
        return result;
    }

    /**
     * 获取返回类型
     *
     * @param pjp
     * @return
     */
    private String getReturnType(ProceedingJoinPoint pjp) {
        try {
            Signature sig = pjp.getSignature();
            if (!(sig instanceof MethodSignature)) {
                throw new IllegalArgumentException("该注解只能用于方法");
            }
            MethodSignature msig = (MethodSignature) sig;
            Object target = pjp.getTarget();
            Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
            return currentMethod.getReturnType().getName();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取缓存的key值
     *
     * @param pjp
     * @param redisCache
     * @return
     */
    private String getCacheKey(ProceedingJoinPoint pjp, RedisCacheable redisCache) {

        StringBuilder buf = new StringBuilder();
        buf.append(pjp.getSignature().getDeclaringTypeName()).append(".").append(pjp.getSignature().getName());
        String keyStr = redisCache.key();
        if (StringUtils.isNotBlank(keyStr)) {
            buf.append(".").append(keyStr);
        }
        //参数
        Object[] args = pjp.getArgs();

        if (redisCache.keyMode() == RedisCacheable.KeyMode.DEFAULT){
            if (args.length > 0){
                for (int i = 0, len = args.length; i < len; i++){
                    if(args[i] instanceof String) {
                        buf.append("#").append(args[i].toString());
                    }
                }
            }
        } else if (redisCache.keyMode() == RedisCacheable.KeyMode.ANNO) {
            Annotation[][] pas = ((MethodSignature) pjp.getSignature()).getMethod().getParameterAnnotations();
            firstLoop:
            for (int i = 0, len = pas.length; i < len; i++) {
                for (int j = 0, len0 = pas[i].length; j < len0; j++) {
                    if (RedisCacheKey.class.isInstance(pas[i][j])) {
                        Object obj = args[i];
                        if (obj instanceof String) {
                            buf.append("#").append(obj);
                        } else if (obj instanceof Integer) {
                            buf.append("#").append(obj.toString());
                        } else if (obj instanceof Long) {
                            buf.append("#").append(obj.toString());
                        } else if (obj instanceof Short) {
                            buf.append("#").append(obj.toString());
                        } else if (obj instanceof Boolean) {
                            buf.append("#").append(obj.toString());
                        }
                        break firstLoop;
                    }
                }
            }
        } else if ((redisCache.keyMode() == RedisCacheable.KeyMode.BEAN)) {
            for (int i = 0, len = args.length; i < len; i++) {
                Field[] fields = args[i].getClass().getFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(RedisCacheKey.class)) {
                        Object value = ReflectionUtils.getField(field, args[i]);
                        buf.append("#").append(value.toString());
                    }
                }
            }
        } else if (redisCache.keyMode() == RedisCacheable.KeyMode.MAP) {
            if (keyStr.length() > 0) {
                Map target = (Map) args[0];
                buf.append("#").append(target.get(keyStr));
            }
        } else if (redisCache.keyMode() == RedisCacheable.KeyMode.BASIC) {

        }

        return buf.toString();
    }
}