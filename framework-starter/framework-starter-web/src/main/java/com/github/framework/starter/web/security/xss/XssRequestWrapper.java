package com.github.framework.starter.web.security.xss;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * xss防御请求包装器
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {

    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        return convert(super.getHeader(name));
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        final Enumeration<String> headers = super.getHeaders(name);
        return new Enumeration<String>() {
            @Override
            public boolean hasMoreElements() {
                return headers.hasMoreElements();
            }

            @Override
            public String nextElement() {
                return convert(headers.nextElement());
            }
        };
    }

    @Override
    public String getParameter(String name) {
        return convert(super.getParameter(name));
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> trustedParameterMap = new HashMap<>();

        Map<String, String[]> parameterMap = super.getParameterMap();
        for (String key : parameterMap.keySet()) {
            String[] trustedValues = null;
            String[] values = parameterMap.get(key);
            if (values != null) {
                trustedValues = new String[values.length];
                for (int i = 0; i < values.length; i++) {
                    trustedValues[i] = convert(values[i]);
                }
            }

            trustedParameterMap.put(key, trustedValues);
        }

        return trustedParameterMap;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }

        String[] trustedValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            trustedValues[i] = convert(values[i]);
        }
        return trustedValues;
    }

    // 将用户输入的特殊字符进行转义
    private String convert(String distrust) {
        return StringEscapeUtils.escapeHtml4(distrust);
    }
}
