package com.example.demo.entity.sys;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Role {

    private String id;
    /**
     * 角色ID
     */
    private String role_id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态
     */
    private int status;
    /**
     * 创建时间
     */
    private Date create_time;
    /**
     * 修改时间
     */
    private Date update_time;

    /**
     * 角色对应权限集合
     */
    private List<Permissions> permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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