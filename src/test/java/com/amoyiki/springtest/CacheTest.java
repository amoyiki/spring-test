package com.amoyiki.springtest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import javax.annotation.Resource;

/**
 * @author amoyiki
 * @since 2019/3/11
 */
public class CacheTest extends SpringTestApplicationTests {
    @Resource
    private CacheManager cacheManager;

    @Test
    public void cacheTest(){
        System.out.println("├ [测试日志]:  " + cacheManager.getCacheNames().toString());
        Cache cache = cacheManager.getCache("myCache");
        cache.put("test", "123");
        System.out.println("├ [获取 cache]: " + cache.get("test").get());
        Assert.assertTrue(cache.get("test").get().equals("123"));
    }
}
