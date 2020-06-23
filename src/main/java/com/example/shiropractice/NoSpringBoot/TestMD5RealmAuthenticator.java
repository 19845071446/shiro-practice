package com.example.shiropractice.NoSpringBoot;


import com.example.shiropractice.NoSpringBoot.Realm.MD5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

public class TestMD5RealmAuthenticator {


    public static void main(String[] args) {
        //创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        MD5Realm realm = new MD5Realm();
//        通过md5+salt 加密验证密码
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //使用md5
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
//        散列1024次
        hashedCredentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        //给安全管理器设置realm
        securityManager.setRealm(realm);
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
