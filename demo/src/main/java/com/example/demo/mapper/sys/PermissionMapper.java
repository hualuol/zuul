package com.example.demo.mapper.sys;

import com.example.demo.entity.sys.Permissions;
import com.example.demo.entity.sys.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface PermissionMapper {

    @Select("select sp.* from sys_role_permission as srp left join sys_permission as sp on srp.role_id=sp.id where srp.role_id=#{roleId}")
    List<Permissions> getByRoleId(String roleId);
}
