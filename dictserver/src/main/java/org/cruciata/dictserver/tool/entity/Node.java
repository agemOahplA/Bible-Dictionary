package org.cruciata.dictserver.tool.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class Node {
    @JSONField(ordinal=1)
    private String id;
    @JSONField(ordinal=2)
    private String parentId;
    @JSONField(ordinal=3)
    private String type;
    @JSONField(ordinal=4)
    private String name;
    @JSONField(ordinal=5)
    private boolean leaf;
    @JSONField(ordinal=6)
    private List<Node> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public List<Node> getChildren() {
        return children==null?children = new ArrayList<>():children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }


}
