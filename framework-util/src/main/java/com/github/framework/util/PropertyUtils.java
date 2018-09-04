package com.github.framework.util;

import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 *
 */
public class PropertyUtils {
    public PropertyUtils() {
    }

    public static String getProperty(String key, String defValue) {
        String value = getProperty(key);
        if(value == null) {
            value = defValue;
        }

        return value;
    }

    public static String getProperty(String key) {
        String value = System.getProperty(key);
        if(value == null) {
            String[] var2 = toEnvKeys(key);
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String envKey = var2[var4];
                value = System.getenv(envKey);
                if(value != null) {
                    break;
                }
            }
        }

        return value;
    }

    public static String[] toEnvKeys(String key) {
        LinkedHashSet keys = new LinkedHashSet();
        keys.addAll(Arrays.asList(toNoCaseChangeEnvKeys(key)));
        keys.addAll(Arrays.asList(toNoCaseChangeEnvKeys(key.toUpperCase())));
        return (String[])keys.toArray(new String[keys.size()]);
    }

    public static String[] toNoCaseChangeEnvKeys(String key) {
        String noDotKey = key.replace('.', '_');
        String noHyphenKey = key.replace('-', '_');
        String noDotNoHyphenKey = noDotKey.replace('-', '_');
        LinkedHashSet keys = new LinkedHashSet();
        keys.add(key);
        keys.add(noDotKey);
        keys.add(noHyphenKey);
        keys.add(noDotNoHyphenKey);
        return (String[])keys.toArray(new String[keys.size()]);
    }
}
