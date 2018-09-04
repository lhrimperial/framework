package com.github.framework.server.shared.domain;

import com.github.framework.server.shared.define.Constants;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class POJO  implements Serializable {
    private static final long serialVersionUID = 2507518245696296782L;

    private Integer tid;
    protected Byte status;
    private String createUser;
    private String modifyUser;
    private Date createTime;
    private Date modifyTime;
    private String active;

    public POJO(){
    }

    public POJO(Byte status, Date createTime, Date modifyTime) {
        this.status = status;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public String getActive() {
        String value = null;
        if (active != null && active != "") {
            value = active;
        } else if (status != null) {
            value = status == Constants.YES ? "Y" : "N";
        }
        return value;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        Byte value = null;
        if (status != null) {
            value = status;
        } else if (this.active != null && this.active != "") {
            value = "Y".equals(active) ? Byte.valueOf("0") : Byte.valueOf("1");
        }
        this.status = value;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
