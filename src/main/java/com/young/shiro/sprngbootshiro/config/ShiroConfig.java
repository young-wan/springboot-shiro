package com.young.shiro.sprngbootshiro.config;

import com.young.shiro.sprngbootshiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

/**
 * @author young
 * @description
 * @date 2021/10/20
 */
@Configuration
public class ShiroConfig {

    // 1.配置对应的ShiroFilter
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(manager);

        // 配置受限资源
        Map<String, String> map = new HashMap<>(16);
        map.put("/user/login", "anon");
        map.put("/user/register", "anon");
        map.put("/register.jsp", "anon");

        map.put("/**", "authc");

        // 设置默认登陆url
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }


    // 2.配置SecurityManger
    @Bean
    public DefaultWebSecurityManager getSecurityManager(Realm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        return manager;
    }


    // 3.配置Realm
    @Bean
    @Primary
    public Realm getRealm() {
        CustomerRealm customerRealm = new CustomerRealm();
        // 修改凭证校验匹配器
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 设置加密算法为MD5
        matcher.setHashAlgorithmName("MD5");
        // 设置散列次数
        matcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(matcher);

        // 开启缓存管理
        customerRealm.setCacheManager(new EhCacheManager());
        // 开启全局缓存
        customerRealm.setCachingEnabled(true);
        // 开启认证缓存
        customerRealm.setAuthenticationCachingEnabled(true);
        customerRealm.setAuthenticationCacheName("authenticationcache");
        // 开启授权缓存
        customerRealm.setAuthorizationCachingEnabled(true);
        customerRealm.setAuthorizationCacheName("authorizationcache");
        return customerRealm;
    }


}
