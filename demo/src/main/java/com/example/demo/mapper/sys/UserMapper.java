package com.example.demo.mapper.sys;

import com.example.demo.entity.sys.CategoryAuthority;
import com.example.demo.entity.sys.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 新增用户
     * @param user
     * @return
     */
    @Insert("insert into sys_user(id,place,account,password,createdAt,isUsed,expireDate) values(#{id},#{place},#{account},#{password},#{createdAt},#{isUsed},#{expireDate})")
    int insert(User user);

    /**
     * shiro获取菜单验证权限
     * @param status
     * @return
     */
    @Select("select * from sys_category_authority where status = #{status} order by sort")
    List<CategoryAuthority> getCategoryAuthority(Integer status);

    /**
     * 根据account查找用户
     * @param account
     * @return
     */
    @Select("select * from sys_user where account=#{account} limit 1")
    User getUserInfoByAccount(String account);
    @Select("select * from sys_user where account=#{account} and password = #{password}")
    User getUserInfo(String account,String password);
}
