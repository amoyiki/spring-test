package com.amoyiki.springtest.service.impl;

import com.amoyiki.springtest.entry.Perm;
import com.amoyiki.springtest.repository.PermRepository;
import com.amoyiki.springtest.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author amoyiki
 * @since 2019/3/8
 */
@Service
public class PermServiceImpl implements PermService {
    @Autowired
    private PermRepository permRepository;

    @Override
    public List<Perm> findPermListByRid(Long rid) {
        return permRepository.findPermListByRid(rid);
    }
}
