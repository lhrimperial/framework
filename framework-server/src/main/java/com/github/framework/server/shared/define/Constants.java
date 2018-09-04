package com.github.framework.server.shared.define;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Constants {

    /**
     * 数据有效状态
     */
    public static final byte PO_ACTIVE = 0;
    public static final byte PO_INACTIVE = 1;

    /**
     * 性别
     */
    public static final byte FEMALE = 0;
    public static final byte MALE = 1;

    /**
     * yes or no
     */
    public static final byte YES = 0;
    public static final byte NO = 1;



    public static enum PoStatus {
        ACTIVE("有效"),INACTIVE("无效");

        private String chName;

        private PoStatus(String chName) {
            this.chName = chName;
        }

        public byte value() {
            return (byte) this.ordinal();
        }

        public static String getCnName(String code) {
            for (PoStatus item : PoStatus.values()) {
                if (item.name().equals(code)) {
                    return item.getChName();
                }
            }
            return code;
        }

        public static Map<String, String> toMap() {
            Map<String, String> map = new HashMap<String, String>();
            for (PoStatus t : values()) {
                map.put(t.name(), t.getChName());
            }
            return map;
        }

        public String getChName() {
            return chName;
        }

        public void setChName(String chName) {
            this.chName = chName;
        }
    }

}
