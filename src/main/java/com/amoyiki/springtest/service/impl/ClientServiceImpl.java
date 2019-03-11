package com.amoyiki.springtest.service.impl;

import com.amoyiki.springtest.entry.Client;
import com.amoyiki.springtest.repository.ClientRepository;
import com.amoyiki.springtest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author amoyiki
 * @since 2019/3/11
 */
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client findByClientId(String clientId) {
        return clientRepository.findByClientId(clientId);
    }

    @Override
    public Client findByClientSercret(String clientSecret) {
        return clientRepository.findByClientSecret(clientSecret);
    }
}
