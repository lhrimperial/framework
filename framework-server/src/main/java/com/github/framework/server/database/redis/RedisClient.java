package com.github.framework.server.database.redis;

import com.github.framework.util.ConfigFileLoadUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
/**
 * @author longhairen
 * @create 2017-06-06 23:14
 * @description
 **/
public class RedisClient implements InitializingBean,DisposableBean {

    public static final String SENTINEL_MODEL = "sentinel";
    public static final String CLUSTER_MODEL = "cluster";
    public static final String SHARED_MODEL = "shared";

    public static String DEFUALT_CONFIG = "redis.properties";

    private int expire = 3600 * 24;

    private IRedisHandler redisHandler;
    private JedisPoolConfig jedisPoolConfig;
    private String configName = DEFUALT_CONFIG;
    private String model;
    private String host;
    private String masterName;
    private int timeout = 2000;
    private String password;

    private StringRedisTemplate redisTemplate;

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String set(String key, String value){
        return redisHandler.setex(key, expire, value);
    }

    public String set(String key, String value, int exp){
        return redisHandler.setex(key, exp, value);
    }

    public String get(String key){
        return redisHandler.get(key);
    }

    public Long del(String key){
        return redisHandler.del(key);
    }

    public Long del(String ...key){
        return redisHandler.del(key);
    }

    public Boolean exists(String key){
        return redisHandler.exists(key);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (redisTemplate != null) {
            redisHandler = new RedisTemplateHandler(redisTemplate);
        } else {
            if (StringUtils.isBlank(model)) {
                loadConfig();
            }
            if (SENTINEL_MODEL.equals(model)) {
                if (StringUtils.isBlank(password)){
                    redisHandler = new RedisSentinelHandler(getSentinelHost(), masterName, jedisPoolConfig);
                } else {
                    redisHandler = new RedisSentinelHandler(getSentinelHost(), masterName, jedisPoolConfig, timeout, password);
                }
            } else if (CLUSTER_MODEL.equals(model)) {
                if (StringUtils.isBlank(password)){
                    redisHandler = new RedisClusterHandler(getClusterHost(), jedisPoolConfig);
                } else {
                    redisHandler = new RedisClusterHandler(getClusterHost(), jedisPoolConfig, timeout, password);
                }
            } else {
                redisHandler = new JedisShardHandler(jedisPoolConfig, getJedisShardInfo());
            }
            redisHandler.init();
        }
    }

    private List<JedisShardInfo> getJedisShardInfo() {
        List<JedisShardInfo> list = new LinkedList<>();
        if (StringUtils.isBlank(host)){
            throw new IllegalArgumentException("host must not be null");
        }
        String [] ipAndPostes = host.split(";");
        if(ipAndPostes != null){
            for (String ipAndPost : ipAndPostes){
                String [] ipAndPostArray = ipAndPost.split(":");
                list.add(new JedisShardInfo(ipAndPostArray[0], Integer.parseInt(ipAndPostArray[1])));
            }
        }
        return list;
    }

    private HashSet<HostAndPort> getClusterHost(){
        if (StringUtils.isBlank(host)){
            throw new IllegalArgumentException("host must not be null");
        }
        HashSet<HostAndPort> clusterNodes = new HashSet<HostAndPort>();
        String [] ipAndPostes = host.split(";");
        if(ipAndPostes != null){
            for (String ipAndPost : ipAndPostes){
                String [] ipAndPostArray = ipAndPost.split(":");
                clusterNodes.add(new HostAndPort(ipAndPostArray[0], Integer.parseInt(ipAndPostArray[1])));
            }
        }
        return clusterNodes;
    }

    private HashSet<String> getSentinelHost(){
        if (StringUtils.isBlank(host)){
            throw new IllegalArgumentException("host must not be null");
        }
        HashSet<String> sentinels = new HashSet<String>();
        String [] ipAndPostes = host.split(";");
        if(ipAndPostes != null){
            for (String ipAndPost : ipAndPostes){
                sentinels.add(ipAndPost);
            }
        }
        return sentinels;
    }

    private void loadConfig(){
        try {
            Properties properties = ConfigFileLoadUtil.getPropertiesForClasspath(configName);
            if (jedisPoolConfig == null) {
                jedisPoolConfig = new JedisPoolConfig();
            }
            jedisPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("redis.maxTotal")));
            jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("redis.maxIdle")));
            jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("redis.minIdle")));
            jedisPoolConfig.setMaxWaitMillis(Long.parseLong(properties.getProperty("redis.maxWaitMillis")));
            jedisPoolConfig.setTestOnBorrow(Boolean.parseBoolean(properties.getProperty("redis.testOnBorrow")));
            jedisPoolConfig.setTestOnReturn(Boolean.parseBoolean(properties.getProperty("redis.testOnReturn")));
            jedisPoolConfig.setTestWhileIdle(Boolean.parseBoolean(properties.getProperty("redis.testWhileIdle")));

            model = properties.getProperty("redis.model");
            host = properties.getProperty("redis.host");
            password = properties.getProperty("redis.password");
            masterName = properties.getProperty("masterName");
            timeout = Integer.parseInt(properties.getProperty("redis.timeout"));
        } catch (FileNotFoundException ee) {
            ee.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() throws Exception {

    }

    public JedisPoolConfig getJedisPoolConfig() {
        return jedisPoolConfig;
    }

    public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
        this.jedisPoolConfig = jedisPoolConfig;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
