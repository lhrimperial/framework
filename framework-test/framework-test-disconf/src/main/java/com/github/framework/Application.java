package com.github.framework;

import com.github.framework.starter.core.ApplicationContexts;
import com.github.framework.starter.core.FrameBootApplication;
import org.springframework.boot.SpringApplication;

/**
 * Hello world!
 *
 */
@FrameBootApplication(appName = "emc-b2b-scenic")
public class Application {

    public static void main(String[] args){
        ApplicationContexts.setProfileIfNotExists("ARK");
        SpringApplication.run(Application.class, args);
    }
}
