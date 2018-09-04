package com.github.framework.test.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 */
@SpringBootApplication
@ComponentScan({"com.github.framework"})
public class MybatisApplication {

    public static void main(String[] args){
        SpringApplication.run(MybatisApplication.class);
    }
}
