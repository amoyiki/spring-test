package com.amoyiki.springtest.config;

//import com.amoyiki.springtest.exception.MyExceptionHandler;

import com.amoyiki.springtest.exception.MyExceptionHandler;
import com.amoyiki.springtest.shiro.ShiroRealm;
import com.amoyiki.springtest.shiro.ShiroTokenManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.LinkedHashMap;

/**
 * @description: shiro 配置类
 * @author: amoyiki
 * @date: 2019/2/28
 */
@Configuration
@Slf4j
public class ShiroConfig {

    @Value("${spring.redis.shiro.host}")
    private String host;
    @Value("${spring.redis.shiro.timeout}")
    private int timeout;
    @Value("${spring.redis.shiro.password}")
    private String password;
    @Value("${spring.redis.shiro.database}")
    private int database;

    /**
     * 密码校验
     *
     * @param
     * @return org.apache.shiro.authc.credential.HashedCredentialsMatcher
     * @author amoyiki
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;

    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        // shiro生命周期处理器
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    /**
     * 自定义sessionManager
     *
     * @param
     * @return org.apache.shiro.session.mgt.SessionManager
     * @author amoyiki
     */
    @Bean
    public SessionManager sessionManager() {
        ShiroTokenManager shiroTokenManager = new ShiroTokenManager();
        shiroTokenManager.setSessionDAO(redisSessionDao());
        return shiroTokenManager;
    }

    /**
     * 配置shiro redisManager
     *
     * @param
     * @return org.crazycake.shiro.RedisManager
     * @author amoyiki
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        // todo value 取不到值
        log.error("├ [redis 配置]: {} {} {} {}", host,database,timeout,password);
        redisManager.setHost(host);
        redisManager.setDatabase(database);
        redisManager.setTimeout(timeout);
        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * 使用shiro-redis 实现 cacheManager
     *
     * @param
     * @return org.crazycake.shiro.RedisCacheManager
     * @author amoyiki
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 使用 shiro-redis
     *
     * @param
     * @return org.crazycake.shiro.RedisSessionDAO
     * @author amoyiki
     */
    @Bean
    public RedisSessionDAO redisSessionDao() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 开启shiro aop 支持
     *
     * @param securityManager
     * @return org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     * @author amoyiki
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(securityManager);
        return attributeSourceAdvisor;
    }

    /**
     * 路由拦截
     *
     * @param securityManager
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @author amoyiki
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        log.debug("ShiroConfiguration.shiroFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置登录url
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        // 设置登录后跳转url
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//        // 是指未授权url
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        // 设定路由验证规则 filterChain 基于短路机制 最先匹配原则
        // 过滤器位置在 org.apache.shiro.web.filter.authc 下
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 静态资源不验证
        filterChainDefinitionMap.put("/static/**", "anon");
//        filterChainDefinitionMap.put("/js/**", "anon");
//        filterChainDefinitionMap.put("/fonts/**", "anon");
//        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        // 登出配置
        filterChainDefinitionMap.put("/logout", "logout");
        // 登录前页面不拦截
//        filterChainDefinitionMap.put("/", "anon");
        // 除了以上链接都需要拦截验证
        filterChainDefinitionMap.put("/**", "authc");
        // 前后端分离，不需要配置登录界面路由
        shiroFilterFactoryBean.setLoginUrl("/unauth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    /**
     *
     * 全局异常处理
     * @author amoyiki
     * @param
     * @return org.springframework.web.servlet.HandlerExceptionResolver
     */
    @Bean(name = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver(){
        return new MyExceptionHandler();
    }
}
