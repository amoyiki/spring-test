package com.amoyiki.springtest.mapper;

import com.amoyiki.springtest.baseMapper.BaseMapper;
import com.amoyiki.springtest.entry.ScheduleJob;

import java.util.List;

public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {
    void updateAllStatus(Integer status);
}