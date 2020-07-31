package com.example.demo.entity.sys;

import java.util.Date;

public class Permissions {

    private String id;
    /**
     * 权限ID
     */
    private String permissions_id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * url
     */
    private String url;

    private String perms;
    /**
     * 父节点ID
     */
    private String parent_id;
    /**
     * 类型
     */
    private String type;
    /**
     * 排序
     */
    private Integer order_num;
    /**
     * 图标
     */
    private String icon;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date create_time;
    /**
     * 修改时间
     */
    private Date update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissions_id() {
        return permissions_id;
    }

    public void setPermissions_id(String permissions_id) {
        this.permissions_id = permissions_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOrder_num() {
        return order_num;
    }

    public void setOrder_num(Integer order_num) {
        this.order_num = order_num;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}