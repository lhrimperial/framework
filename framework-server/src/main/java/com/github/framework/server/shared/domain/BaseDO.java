package com.github.framework.server.shared.domain;

import com.github.framework.server.shared.define.Constants;
import com.github.framework.util.string.StringUtils;

/**
 * 基础领域对象，业务实体
 */
public class BaseDO extends POJO {

    private String active;

    public String getActive() {
        if (StringUtils.isEmpty(active)) {
            if (status != null) {
                return status == Constants.PO_ACTIVE ? "Y" : "N";
            }
        }
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
