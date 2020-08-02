package com.example.demo.service.sys;

import com.example.demo.entity.sys.Role;

import java.util.List;

public interface RoleService {
    /**
     * 获取用户角色列表
     * @param userId
     * @return
     */
    List<Role> getByUserId(String userId);
}
