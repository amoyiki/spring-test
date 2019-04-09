package com.amoyiki.springtest.service;

import com.amoyiki.springtest.entry.Perm;

import java.util.List;

/**
 * @author amoyiki
 * @date 2019/3/8
 */
public interface PermService {
    List<Perm> findPermListByRid(Integer rid);
}
