package com.example.shiropractice.WithSpringBoot.controller;

import com.example.shiropractice.WithSpringBoot.mapper.UserMapper;
import com.example.shiropractice.WithSpringBoot.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserMapper userMapper;

    @RequestMapping("login")
    public String login(String username, String password) {
        System.out.println(username);
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            return "yes";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "no";
    }

    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "logout";
    }

    @RequestMapping("register")
    public String register(User user) {
//        生成随机盐
        String salt = "随机";
        user.setSalt(salt);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        userMapper.save(user);
        return "success";
    }

}
