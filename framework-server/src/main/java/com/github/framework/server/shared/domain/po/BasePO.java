package com.github.framework.server.shared.domain.po;

import com.github.framework.server.shared.domain.POJO;

import java.util.Date;


/**
 * 基础持久化对象
 */
public class BasePO extends POJO {

    public BasePO(){}

    public BasePO(Byte status, Date createTime, Date modifyTime) {
        super(status, createTime, modifyTime);
    }
}
