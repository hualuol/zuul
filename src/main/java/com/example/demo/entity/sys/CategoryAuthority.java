package com.example.demo.entity.sys;

public class CategoryAuthority {

    private String id;
    //菜单
    private String category;
    //权限     anon(需要权限) 或  authc(不需要权限)  或  指定角色(例如：roles[admin])
    private String authority;

    private String regionCode;
    //状态  0有效 1失效
    private Integer status;
    //插入顺序
    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
