package com.github.framework.test.mybatis;

import com.github.framework.starter.core.FrameBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 */
//@SpringBootApplication
//@ComponentScan({"com.github.framework"})
@FrameBootApplication(appName = "mybatis")
@EnableTransactionManagement
public class MybatisApplication {

    public static void main(String[] args){
        SpringApplication.run(MybatisApplication.class);
    }
}
