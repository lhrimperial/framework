package com.github.framework.server.shared.entity.node;

import com.github.framework.server.shared.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 树节点的实体对象
 */
@SuppressWarnings("rawtypes")
public class TreeNode<T extends BaseEntity, K extends TreeNode>{

    /**
     * 树节点ID
     */
    private String id;

    /**
     * 树节点文本显示
     */
    private String text;

    /**
     * 是否叶子节点
     */
    private Boolean leaf;

    /**
     * 父节点ID
     */
    private String parentId;

    /**
     * 显示是否选择
     */
    private Boolean checked;

    /**
     * 节点对象数据
     */
    private T entity;

    /**
     * 孩子节点
     */
    private List<K> children = new ArrayList<K>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public List<K> getChildren() {
        return children;
    }

    public void setChildren(List<K> children) {
        this.children = children;
    }
}