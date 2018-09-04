package com.github.framework.starter.core;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.Assert;

/**
 *
 **/
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FrameApplicationRunListener implements SpringApplicationRunListener {

    private SpringApplication springApplication;
    private ApplicationArguments applicationArguments;

    public FrameApplicationRunListener(SpringApplication springApplication, String[] args) {
        this.springApplication = springApplication;
        this.applicationArguments = new DefaultApplicationArguments(args);
        initApp(springApplication);
    }

    @Override
    public void starting() {

    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        ApplicationContexts.setEnvironment(environment);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        ApplicationContexts.setApplicationContext(context);
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    @Override
    public void finished(ConfigurableApplicationContext context, Throwable exception) {

    }

    // 初始化
    private void initApp(SpringApplication springApplication) {
        FrameBootApplication annotation = null;
        for (Object source : springApplication.getSources()) {
            if (!(source instanceof Class)) {
                continue;
            }
            annotation = AnnotatedElementUtils.findMergedAnnotation((Class) source, FrameBootApplication.class);
            if (annotation != null) {
                break;
            }
        }
        Assert.notNull(annotation, "sources中无@FrameBootApplication");

        ApplicationContexts.init(annotation.appName());
    }
}
