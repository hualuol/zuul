package com.example.demo.service.sys;

import com.example.demo.entity.sys.Permissions;
import com.example.demo.mapper.sys.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;


    @Override
    public List<Permissions> getByRoleId(String roleId) {
        return permissionMapper.getByRoleId(roleId);
    }
}
