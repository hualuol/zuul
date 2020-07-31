package com.example.demo.service.sys;

import com.example.demo.entity.sys.Permissions;

import java.util.List;

public interface PermissionService {
    /**
     * 获取角色的权限
     * 可以是菜单权限
     * @param roleId
     * @return
     */
    List<Permissions> getByRoleId(String roleId);
}
