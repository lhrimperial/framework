package com.github.framework.starter.logging.support;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.github.framework.starter.core.ApplicationContexts;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 */
public class FramePatternLayoutEncoder extends PatternLayoutEncoder {

    private String appName;
    private String hostName;

    public FramePatternLayoutEncoder() {
        super();
    }

    @Override
    public void doEncode(ILoggingEvent event) throws IOException {
        String txt = this.layout.doLayout(event);
        this.outputStream.write(this.convertToBytes(frameLayout(event, txt)));

        if(isImmediateFlush()) {
            this.outputStream.flush();
        }
    }

    private String frameLayout(ILoggingEvent event, String text) {
        this.appName = ApplicationContexts.getAppName();
        this.hostName = ApplicationContexts.getHostName();
        StringBuffer sb = new StringBuffer(text);
        sb.append(" [FLogS] APP:\"").append(appName).append("\" HOST:\"").append(hostName).append("\" track:\"").append(LogTrackContext.getTrackToken()).append("\"\n");
        return sb.toString();
    }

    private byte[] convertToBytes(String s) {
        if (getCharset() == null) {
            return s.getBytes();
        } else {
            try {
                return s.getBytes(getCharset().name());
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException("An existing charset cannot possibly be unsupported.");
            }
        }
    }
}
