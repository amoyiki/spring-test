package com.amoyiki.springtest.enums;

import lombok.Getter;

/**
 * 数据源名称
 *
 * @author amoyiki
 * @date 2019/4/9
 */
@Getter
public enum DataSourceEnum {
	DB1("db1"),
	DB2("db2"),
	;
	private String value;

	DataSourceEnum(String value) {
		this.value = value;
	}
}
