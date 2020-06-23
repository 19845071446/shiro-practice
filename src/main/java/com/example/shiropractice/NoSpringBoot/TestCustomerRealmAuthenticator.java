package com.example.shiropractice.NoSpringBoot;


import com.example.shiropractice.NoSpringBoot.Realm.CustomerRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

public class TestCustomerRealmAuthenticator {


    public static void main(String[] args) {
        //创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //给安全管理器设置realm
        securityManager.setRealm(new CustomerRealm());
        //全局安全工具类
        SecurityUtils.setSecurityManager(securityManager);
        //关键对象
        Subject subject = SecurityUtils.getSubject();
        //创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("xiaochen", "123");
        try {
            subject.login(token);//认证
            System.out.println(subject.isAuthenticated());
        } catch (Exception e) {
            e.printStackTrace();
        }

//       认证用户进行授权
        if (subject.isAuthenticated()) {
//            基于角色权限控制
            System.out.println(subject.hasRole("adsf"));
//            基于多角色控制(同时具有哪些角色)
            System.out.println(subject.hasAllRoles(Arrays.asList("admin", "user")));
//            是否具有其中一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "power"));
            for (boolean a : booleans)
                System.out.println(a);
            System.out.println("=====================================================");
            //基于资源控制
            System.out.println(subject.isPermitted("user:*:*"));
//            分别具有哪些权限
            boolean[] permitted = subject.isPermitted("user:*:*", "poi:create");
//            同时具有哪些权限
            System.out.println(subject.isPermittedAll("user:*:*","poi:create"));

        }

    }


}
