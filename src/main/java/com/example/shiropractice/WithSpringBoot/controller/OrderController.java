package com.example.shiropractice.WithSpringBoot.controller;

import com.example.shiropractice.WithSpringBoot.mapper.UserMapper;
import com.example.shiropractice.WithSpringBoot.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {


    @RequestMapping("saveByCode")
    public String saveByCode() {
        //代码方式
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            return "save";
        }else {
            return "nosave";
        }
//        基于权限字符串
    }

    @RequestMapping("save1")
//    @RequiresRoles("admin")//判断角色
//    @RequiresRoles(value = {"user","admin"}) 同时具有相同角色
    @RequiresPermissions("user:delete") //用于资源授权验证
    public String saveByAnnocation() {
        return "save";
    }


}
