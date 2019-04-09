package com.amoyiki.springtest.aspect;

import com.amoyiki.springtest.annotation.DataSource;
import com.amoyiki.springtest.config.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 数据源 切面
 * @author amoyiki
 * @date 2019/4/9
 */
@Component
@Slf4j
@Aspect
@Order(-1)
public class DataSourceAspect {
	@Pointcut("@within(com.amoyiki.springtest.annotation.DataSource) " +
			"|| @ annotation(com.amoyiki.springtest.annotation.DataSource)")
	public void pointCut(){}

	@Before("pointCut() && @annotation(dataSource)")
	public void doBefore(DataSource dataSource){
		log.info("├ [数据源切面]: 选择数据源: {}", dataSource.value().getValue());
	}

	@After("pointCut()")
	public void doAfter(){
		DataSourceContextHolder.clear();
	}
}
