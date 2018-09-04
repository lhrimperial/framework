package com.github.framework.server.configure;

import com.github.framework.server.cache.message.MessageCache;
import com.github.framework.server.cache.message.MessageCacheProvider;
import com.github.framework.server.web.message.MessageBundle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class MessageConfiguration {

    @Bean
    public MessageCacheProvider messageCacheProvider() {
        return new MessageCacheProvider();
    }

    @Bean
    public MessageCache messageCache() {
        return new MessageCache();
    }

    @Bean
    public MessageBundle messageBundle() {
        return new MessageBundle();
    }

}
