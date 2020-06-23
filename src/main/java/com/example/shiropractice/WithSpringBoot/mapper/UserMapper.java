package com.example.shiropractice.WithSpringBoot.mapper;

import com.example.shiropractice.WithSpringBoot.pojo.Permission;
import com.example.shiropractice.WithSpringBoot.pojo.Role;
import com.example.shiropractice.WithSpringBoot.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public User selectById(String id) {
        return null;
    }

    public void save(User user) {

    }

    public User findByuserName(String username) {
        return null;
    }

    public List<Role> findRolesByUsername(String username) {
        return null;
    }

    //根据角色id获取权限集合
    public List<Permission> findPermsByRoleId(String roleId) {
        return null;
    }
}
