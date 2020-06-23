package com.example.shiropractice.NoSpringBoot;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class TestAuthenticator {


    public static void main(String[] args) {
        //创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //给安全管理器设置realm
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        //全局安全工具类
        SecurityUtils.setSecurityManager(securityManager);
        //关键对象
        Subject subject = SecurityUtils.getSubject();
        //创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("xiaochen","123");
        try {
            subject.login(token);//认证
        }catch (Exception e){
            e.printStackTrace();
        }



    }



}
