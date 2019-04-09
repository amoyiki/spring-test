package com.amoyiki.springtest.service.impl;

import com.amoyiki.springtest.entry.Perm;
import com.amoyiki.springtest.mapper.PermMapper;
import com.amoyiki.springtest.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author amoyiki
 * @date 2019/3/8
 */
@Service
public class PermServiceImpl implements PermService {
    @Autowired
    private PermMapper permMapper;

    @Override
    public List<Perm> findPermListByRid(Integer rid) {
        return permMapper.findPermListByRid(rid);
    }
}
