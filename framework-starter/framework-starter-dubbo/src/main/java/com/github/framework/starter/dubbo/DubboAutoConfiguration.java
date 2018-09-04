package com.github.framework.starter.dubbo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 */
@Configuration
@Import({DubboProviderLauncher.class,DubboConsumerLauncher.class})
public class DubboAutoConfiguration {
}
