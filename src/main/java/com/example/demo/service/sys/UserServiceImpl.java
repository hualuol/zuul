package com.example.demo.service.sys;

import com.example.demo.entity.sys.CategoryAuthority;
import com.example.demo.entity.sys.Role;
import com.example.demo.entity.sys.User;
import com.example.demo.mapper.sys.UserMapper;
import com.example.demo.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private RoleService roleService;
    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User findByAccount(String account,String password) {
        User user=null;
        if(redisUtil.hasKey("user-"+account)){
            user = (User) redisUtil.get("user-"+account);
        }else{
            user = userMapper.getUserInfo(account,password);
            configUser(user);
            redisUtil.set("user-"+user.getAccount(),user);
        }
        return user;
    }
    @Override
    public List<CategoryAuthority> getCategoryAuthority(Integer status){
        List<CategoryAuthority> list = userMapper.getCategoryAuthority(status);
        return  list;
    }

    @Override
    public User getUserInfoByAccount(String account) {
        User user=null;
        if(redisUtil.hasKey("user-"+account)){
            user = (User) redisUtil.get("user-"+account);
        }else{
            user = userMapper.getUserInfoByAccount(account);
            configUser(user);
        }
        return user;
    }

    /**
     * 配置用户
     * 主要是配置用户拥有的角色，角色拥有的权限
     * @param user
     * @return
     */
    private User configUser(User user){
        List<Role> roles = roleService.getByUserId(user.getId());
        user.setRoles(roles);
        return user;
    }
}
