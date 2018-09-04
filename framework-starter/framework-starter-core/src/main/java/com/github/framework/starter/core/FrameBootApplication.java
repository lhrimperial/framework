package com.github.framework.starter.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.annotation.*;

/**
 *
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootApplication
public @interface FrameBootApplication  {

    /**
     * 应用名
     */
    String appName();

}
