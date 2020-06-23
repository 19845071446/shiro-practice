package com.example.shiropractice.NoSpringBoot.Realm;

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
        //PrincipalCollection是身份集合 一个subject可以有多个身份信息 获取主身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println(primaryPrincipal);
//        根据身份信息从数据库获取相关权限赋值给SimpleAuthorizationInfo
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRole("user");
//        将资源权限赋值给主体  资源标识符：操作：资源类型
        simpleAuthorizationInfo.addStringPermission("user:*:*");

        return simpleAuthorizationInfo;
    }
//认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        获取用户名
        String principal = (String) authenticationToken.getPrincipal();
//        根据用户名（身份信息）查询数据库
        if("xiaochen".equals(principal)){
//           参数为： 数据库中的用户名 密码 当前Realm中的名字
            return new SimpleAuthenticationInfo("xiaochen","123",this.getName());
        }
        return null;
    }
}
