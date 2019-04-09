package com.amoyiki.springtest.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author amoyiki
 * @date 2019/4/9
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSource();
	}
}
