package com.example.shiropractice.NoSpringBoot.Realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MD5Realm extends AuthorizingRealm {
//授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
//认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        获取用户名
        String principal = (String) authenticationToken.getPrincipal();
//        根据用户名（身份信息）查询数据库
        if("xiaochen".equals(principal)){
            return new SimpleAuthenticationInfo("xiaochen","8a56f43s...",
//                    随机盐 salt
                    ByteSource.Util.bytes("adsfsf")
                    ,this.getName());
        }
        return null;
    }
}
