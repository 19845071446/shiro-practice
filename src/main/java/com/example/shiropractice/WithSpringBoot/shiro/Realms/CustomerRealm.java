package com.example.shiropractice.WithSpringBoot.shiro.Realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomerRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        //根据身份信息获取角色详细信息
        if ("xiaochen".equals(primaryPrincipal)) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//            基于角色
            simpleAuthorizationInfo.addRole("admin");
//            基于资源
            simpleAuthorizationInfo.addStringPermission("user:*:*");

            return simpleAuthorizationInfo;
        }
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        获取用户名
        String principal = (String) authenticationToken.getPrincipal();
        System.out.println(principal);
//        根据用户名（身份信息）查询数据库
        if ("xiaochen".equals(principal)) {
//           参数为： 数据库中的用户名 密码 当前Realm中的名字
            return new SimpleAuthenticationInfo("xiaochen", "123", this.getName());
        }
        return null;
    }
}
