package com.example.shiropractice.WithSpringBoot.config;

import com.example.shiropractice.WithSpringBoot.shiro.Realms.CustomerRealm;
import com.example.shiropractice.WithSpringBoot.shiro.cache.RedisCacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/*shiro配置类*/
@Configuration
public class ShiroConfig {
    //创建shirofilter 拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> map = new HashMap<>();
        //配置系统公共资源 公共资源先配置 然后在配置受限资源
        map.put("/user/login", "anon");
        //配置系统受限资源
        //authc表示需要认证和过滤的过滤器 还有很多
        map.put("/**", "authc");
        //默认系统路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);


        return shiroFilterFactoryBean;
    }

    //创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    /*创建自定义realm*/
//    @Bean("realm")
//    public Realm getRealm(){
//        return new CustomerRealm();
//    }
    /*md5Realm*/
    @Bean("realm")
    public Realm getRealm() {
        CustomerRealm customerRealm = new CustomerRealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //设置加密算法
        matcher.setHashAlgorithmName("MD5");
        //设置迭代次数
        matcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(matcher);

        //开启缓存
        customerRealm.setCacheManager(new RedisCacheManager());
        customerRealm.setCachingEnabled(true);//开启全局缓存
        customerRealm.setAuthenticationCachingEnabled(true);//开启认证缓存
        customerRealm.setAuthenticationCacheName("Authentication");
        customerRealm.setAuthorizationCachingEnabled(true);//开启授权缓存
        customerRealm.setAuthorizationCacheName("Authorization");
        return customerRealm;
    }

}
