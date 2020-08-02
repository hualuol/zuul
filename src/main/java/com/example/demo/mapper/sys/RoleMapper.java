package com.example.demo.mapper.sys;

import com.example.demo.entity.sys.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface RoleMapper {
    @Select("select sr.* from sys_user_role as sur left join sys_role as sr on sur.role_id=sr.id where sur.user_id=#{userId}")
    List<Role> getByUserId(String userId);
}
