package com.github.framework.server.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 */
@Configuration
@Import({MessageConfiguration.class})
public class ServerAutoConfiguration {


}
