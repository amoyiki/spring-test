package com.amoyiki.springtest.service.impl;
import com.amoyiki.springtest.service.AuthorizeService;
import com.amoyiki.springtest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author amoyiki
 * @date 2019/3/11
 */
@Service
public class AuthorizeServiceImpl implements AuthorizeService {
    @Autowired
    private ClientService clientService;
    @Resource
    private CacheManager cacheManager;
    private Cache cache;

    /**
     *
     * 注入 ehcache
     * @author amoyiki
     * @param
     * @return
     */
    @PostConstruct
    private void init(){
        System.out.println("此方法在依赖注入后自动调用: " +cacheManager);
        this.cache = cacheManager.getCache("myCache");
    }
    @Override
    public boolean checkClientId(String clientId) {
        return clientService.findByClientId(clientId) != null;
    }

    @Override
    public void addAuthCode(String authCode, String name) {
        cache.put(authCode, name);
    }

    @Override
    public boolean checkClientSecret(String clientSecret) {
        return clientService.findByClientSercret(clientSecret) != null;
    }

    @Override
    public boolean checkAuthCode(String authCode) {
        return cache.get(authCode) != null;
    }

    @Override
    public String getUsernameByAuthCode(String authCode) {
        return (String) cache.get(authCode).get();
    }

    @Override
    public void addAccessToken(String accessToken, String username) {
        cache.put(accessToken, username);
    }

    @Override
    public long getExpireIn() {
        return 3600L;
    }

    @Override
    public boolean checkAccessToken(String accessToken) {
        return cache.get(accessToken) != null;
    }

    @Override
    public String getUsernameByAccessToken(String accessToken) {
        return (String) cache.get(accessToken).get();
    }
}
