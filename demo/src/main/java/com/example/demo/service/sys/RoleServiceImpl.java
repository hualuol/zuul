package com.example.demo.service.sys;

import com.example.demo.entity.sys.Permissions;
import com.example.demo.entity.sys.Role;
import com.example.demo.mapper.sys.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private  PermissionService permissionService;
    @Override
    public List<Role> getByUserId(String userId) {
        List<Role> roles = roleMapper.getByUserId(userId);
        for(Role role : roles){
            List<Permissions> list=permissionService.getByRoleId(role.getRole_id());
            role.setPermissions(list);
        }
        return roles;
    }
}
