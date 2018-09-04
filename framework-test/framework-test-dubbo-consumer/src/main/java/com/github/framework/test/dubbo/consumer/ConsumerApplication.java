package com.github.framework.test.dubbo.consumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author longhairen
 * @create 2018-02-23 21:30
 * @description
 **/
@SpringBootApplication
@EnableDubboConfiguration
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }
}
