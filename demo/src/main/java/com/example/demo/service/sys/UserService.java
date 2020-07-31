package com.example.demo.service.sys;

import com.example.demo.entity.sys.CategoryAuthority;
import com.example.demo.entity.sys.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 插入
     * @param user
     * @return
     */
    public int insert(User user);

    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    public User findByAccount(String account,String password);

    /**
     * 获取目录菜单访问权限
     * @param status
     * @return
     */
    List<CategoryAuthority> getCategoryAuthority(Integer status);

    /**
     * 根据账号从缓存中查找信息、
     * 找不到从数据库找
     * 再找不到返回异常
     * @param account
     * @return
     */
    public User getUserInfoByAccount(String account);
}
