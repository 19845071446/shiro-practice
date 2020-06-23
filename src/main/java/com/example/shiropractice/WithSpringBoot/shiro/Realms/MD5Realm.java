package com.example.shiropractice.WithSpringBoot.shiro.Realms;

import com.example.shiropractice.WithSpringBoot.mapper.UserMapper;
import com.example.shiropractice.WithSpringBoot.pojo.Permission;
import com.example.shiropractice.WithSpringBoot.pojo.Role;
import com.example.shiropractice.WithSpringBoot.pojo.User;
import com.example.shiropractice.WithSpringBoot.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class MD5Realm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        UserMapper mapper = (UserMapper) ApplicationContextUtils.getBean("userMapper");
        List<Role> rolesByUsername = mapper.findRolesByUsername(primaryPrincipal);
//        角色信息获取
        if(!CollectionUtils.isEmpty(rolesByUsername)){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            for (Role role : rolesByUsername) {
                simpleAuthorizationInfo.addRole(role.getName());
                //权限信息
                List<Permission> permsByRoleId = mapper.findPermsByRoleId(role.getId());
                if(!CollectionUtils.isEmpty(permsByRoleId)){
                    for (Permission permission : permsByRoleId) {
                        simpleAuthorizationInfo.addStringPermission(permission.getName());
                    }
                }
            }
            return simpleAuthorizationInfo;
        }
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        获取用户名
        String principal = (String) authenticationToken.getPrincipal();
        UserMapper mapper = (UserMapper) ApplicationContextUtils.getBean("userMapper");
        User user = mapper.findByuserName(principal);
        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword()
                    ,ByteSource.Util.bytes(user.getSalt()),this.getName());
        }
        return null;
    }
}
