package com.github.framework.starter.redis.boot;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
@AutoConfigureAfter(value = RedisCacheConfiguration.class)
public class RedisClientConfiguration {

}
