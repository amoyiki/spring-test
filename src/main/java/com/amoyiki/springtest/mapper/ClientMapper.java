package com.amoyiki.springtest.mapper;

import com.amoyiki.springtest.baseMapper.BaseMapper;
import com.amoyiki.springtest.entry.Client;

public interface ClientMapper extends BaseMapper<Client> {
    Client findByClientId(String clientId);

    Client findByClientSecret(String clientSecret);
}