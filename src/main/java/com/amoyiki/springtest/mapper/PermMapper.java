package com.amoyiki.springtest.mapper;

import com.amoyiki.springtest.baseMapper.BaseMapper;
import com.amoyiki.springtest.entry.Perm;

import java.util.List;

public interface PermMapper extends BaseMapper<Perm> {
    List<Perm> findPermListByRid(Integer rid);
}