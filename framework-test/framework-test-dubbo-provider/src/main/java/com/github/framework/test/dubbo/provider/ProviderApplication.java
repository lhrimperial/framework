package com.github.framework.test.dubbo.provider;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author longhairen
 * @create 2018-02-23 21:39
 * @description
 **/
@SpringBootApplication
@EnableDubboConfiguration
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class);
    }
}
