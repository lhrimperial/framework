package com.github.framework.server.shared.entity;

import java.io.Serializable;
import java.util.Date;


/**
* @ClassName: BaseEntity
* @Description: TODO
* @author 龙海仁
* @date 2016年3月19日下午5:34:22
*
*/

public abstract class BaseEntity implements Serializable, IEntity, Comparable<IEntity> {
    
    private static final long serialVersionUID = 1372509362360011358L;
    
    private String id;// ID
    private Date createTime;// 创建日期
    private String createUser;//创建人
    private Date modifyTime;//修改时间
    private String modifyUser;//修改时间
    
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	@Override
    public int hashCode() {
        return id == null ? System.identityHashCode(this) : id.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass().getPackage() != obj.getClass().getPackage()) {
            return false;
        }
        if (IEntity.class.isAssignableFrom((obj.getClass()))) {
            final BaseEntity other = (BaseEntity) obj;
            if (id == null) {
                if (other.getId() != null) {
                    return false;
                }
            } else if (!id.equals(other.getId())) {
                return false;
            }
            return true;
        }
        return false;
    }
    
    @Override
    public int compareTo(IEntity o) {
        if (null == o)
            return 0;
        return (o.equals(this) ? 0 : 1);
    }
    
}
