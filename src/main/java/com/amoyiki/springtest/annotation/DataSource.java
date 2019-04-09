package com.amoyiki.springtest.annotation;

import com.amoyiki.springtest.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * 自定义注解 默认数据源为 db1
 * @author amoyiki
 * @date 2019/4/9
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
	DataSourceEnum value() default DataSourceEnum.DB1;
}
