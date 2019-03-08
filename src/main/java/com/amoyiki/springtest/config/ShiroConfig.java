package com.amoyiki.springtest.config;

import com.amoyiki.shirotest.shiro.ShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @description: shiro 配置类
 * @author: amoyiki
 * @date: 2019/2/28
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置登录url
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 设置登录后跳转url
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 是指未授权url
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        // 设定路由验证规则 filterChain 基于短路机制 最先匹配原则
        // 过滤器位置在 org.apache.shiro.web.filter.authc 下
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 静态资源不验证
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        // 登出配置
        filterChainDefinitionMap.put("/logout", "logout");
        // 登录前页面不拦截
        filterChainDefinitionMap.put("/", "anon");
        // 除了以上链接都需要拦截验证
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        // 配置 securityManager, 注入 shiroRealm
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        // shiro生命周期处理器
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        return shiroRealm;
    }

}
