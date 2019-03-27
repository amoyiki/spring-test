package com.amoyiki.springtest.baseMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/** 通用Mapper
 * @author amoyiki
 * @since 2019/3/27
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
