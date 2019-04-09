package com.amoyiki.springtest.service.impl;

import com.amoyiki.springtest.entry.Client;
import com.amoyiki.springtest.mapper.ClientMapper;
import com.amoyiki.springtest.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author amoyiki
 * @date 2019/3/11
 */
@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public Client findByClientId(String clientId) {
        log.info("{}",clientMapper.findByClientId(clientId));
        return clientMapper.findByClientId(clientId);
    }

    @Override
    public Client findByClientSercret(String clientSecret) {
        return clientMapper.findByClientSecret(clientSecret);
    }
}
