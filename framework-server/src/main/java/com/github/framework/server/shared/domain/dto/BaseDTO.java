package com.github.framework.server.shared.domain.dto;

import com.github.framework.server.shared.define.Constants;
import com.github.framework.server.shared.domain.POJO;
import com.github.framework.util.string.StringUtils;


/**
 * 基础传输对象
 */
public class BaseDTO  extends POJO {

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
