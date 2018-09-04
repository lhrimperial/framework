package com.github.framework.archetype;

import com.github.framework.starter.core.ApplicationContexts;
import com.github.framework.starter.core.FrameBootApplication;
import org.springframework.boot.SpringApplication;

/**
 *
 */
@FrameBootApplication(appName = "archetype")
public class Main {

    public static void main(String[] args){
        ApplicationContexts.setProfileIfNotExists("dev");
        SpringApplication.run(Main.class);
    }
}
