package com.amoyiki.springtest.service;

import com.amoyiki.springtest.entry.Client;

/**
 * @author amoyiki
 * @since 2019/3/11
 */
public interface ClientService {
    /**
     *
     * 根据 Client Id 获取 Client 对象
     * @author amoyiki
     * @param clientId
     * @return com.amoyiki.springtest.entry.Client
     */
    Client findByClientId(String clientId);

    /**
     *
     * 根据 Client Secret 获取 Client 对象
     * @author amoyiki
     * @param clientSecret
     * @return com.amoyiki.springtest.entry.Client
     */
    Client findByClientSercret(String clientSecret);
}
