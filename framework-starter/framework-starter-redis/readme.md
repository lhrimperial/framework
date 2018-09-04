
## redis使用说明

为防止不同应用key冲突，对redis增加命名空间功能：从spring容器获取到的RedisTemplate和StringRedisTemplate增加命名空间，命名空间为各自应用的appName。

### 1. 引入redis依赖：
```
<dependency>
    <artifactId>framework-starter-redis</artifactId>
    <groupId>com.github.framework</groupId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
### 2. 配置：
```
spring.redis.cluster.nodes=192.168.204.128:6379
spring.redis.timeout=1000
spring.redis.cluster.max-redirects=5
spring.redis.pool.max-active=30
spring.redis.pool.max-idle=10
spring.redis.pool.min-idle=10
spring.redis.pool.max-wait=3000
```
### 3. 使用redis：

- RedisTemplate使用：

```
    @Autowired
    private RedisTemplate redisTemplate;

    public void test(){
        // 删除缓存
        redisTemplate.delete("key3");
        // 设置有效期100秒
        redisTemplate.expire("key4", 100, TimeUnit.SECONDS);
    }
```
- 注解式缓存使用：

```
    @Component
    public class CacheTest {
    
        @Autowired
        private PersonRepository personRepository;
    
        @Override
        //@CachePut缓存新增的或更新的数据到缓存,其中缓存名字是 people 。数据的key是person的id
        @CachePut(value = "people", key = "#person.id")
        public Person save(Person person) {
            Person p = personRepository.save(person);
            System.out.println("为id、key为:"+p.getId()+"数据做了缓存");
            return p;
        }
    
        @Override
        //@CacheEvict 从缓存people中删除key为id 的数据
        @CacheEvict(value = "people")
        public void remove(Long id) {
            System.out.println("删除了id、key为"+id+"的数据缓存");
            //这里不做实际删除操作
        }
    
        @Override
        //@Cacheable缓存key为person 的id 数据到缓存people 中,如果没有指定key则方法参数作为key保存到缓存中。
        @Cacheable(value = "people", key = "#person.id")
        public Person findOne(Person person) {
            Person p = personRepository.findOne(person.getId());
            System.out.println("为id、key为:"+p.getId()+"数据做了缓存");
            return p;
        }

    }
```