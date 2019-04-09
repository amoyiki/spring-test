package com.amoyiki.springtest.config;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

/**
 * Ehcache 配置类
 * @author amoyiki
 * @date 2019/3/11
 */
@Slf4j
public class EhcacheConfig {
    @Bean
    public EhCacheManagerFactoryBean ehcacheManagerFactoryBean(){
        log.info("├ [缓存配置]: ehcacheManagerFactoryBean");
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        // 设置 shared 决定该 ehcache 是 Spring 独用还是和其他框架共用
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }
    /**
     *
     * 主要管理器
     * @author amoyiki
     * @param
     * @return org.springframework.cache.ehcache.EhCacheCacheManager
     */
    @Bean(name = "appEhCacheCacheManager")
    public EhCacheCacheManager ehCacheCacheManager(){
        log.info("├ [缓存配置]: ehCacheCacheManager");
        return new EhCacheCacheManager(ehcacheManagerFactoryBean().getObject());
    }
}
