package com.github.framework.starter.logging.support;

import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 *
 */
public class LogTrackContext {

    private static final ThreadLocal<String> trackTokens = new ThreadLocal<>();

    public LogTrackContext() {
    }

    public static void initTrackToken() {
        setTrackToken(StringUtils.replace(java.util.UUID.randomUUID().toString(), "-", "").toUpperCase());
    }

    public static void setTrackToken(String token) {
        trackTokens.set(token);
    }

    public static String getTrackToken() {
        return trackTokens.get();
    }

    public static void remove() {
        trackTokens.remove();
    }
}
