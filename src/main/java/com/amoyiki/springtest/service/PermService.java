package com.amoyiki.springtest.service;

import com.amoyiki.springtest.entry.Perm;

import java.util.List;

/**
 * @author amoyiki
 * @since 2019/3/8
 */
public interface PermService {
    List<Perm> findPermListByRid(Long rid);
}
