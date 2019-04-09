package com.amoyiki.springtest.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.amoyiki.springtest.enums.DataSourceEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author amoyiki
 * @date 2019/3/26
 */
@Configuration
@MapperScan(basePackages = "com.amoyiki.springtest.mapper",
		sqlSessionFactoryRef = "sqlSessionFactory")
public class DruidConfig {

	@Value("${mybatis.type-aliases-package}")
	private String typeAliasesPackage;
	@Value("${mybatis.mapper-locations}")
	private String mapperLocations;

	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSource dataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	// 动态数据源
	@Primary
	@Bean(name = "db1")
	@ConfigurationProperties(prefix = "spring.datasource.druid.db1")
	public DataSource db1() {
		return DataSourceBuilder.create().build();
	}


	@Bean(name = "db2")
	@ConfigurationProperties(prefix = "spring.datasource.druid.db2")
	public DataSource db2() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * 动态数据源配置
	 *
	 * @return javax.sql.DataSource
	 * @author amoyiki
	 */
	@Bean
	public DataSource multipleDataSource() {

		MultipleDataSource multipleDataSource = new MultipleDataSource();
		Map<Object, Object> targetDataSource = new HashMap<>();
		targetDataSource.put(DataSourceEnum.DB1.getValue(), db1());
		targetDataSource.put(DataSourceEnum.DB2.getValue(), db1());
		// 添加数据源
		multipleDataSource.setTargetDataSources(targetDataSource);
		// 设置默认源
		multipleDataSource.setDefaultTargetDataSource(db1());
		return multipleDataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("multipleDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
		return sqlSessionFactoryBean.getObject();
	}

	/**
	 * 事务
	 *
	 * @param dataSource
	 * @return org.springframework.transaction.PlatformTransactionManager
	 * @author amoyiki
	 */
	@Bean
	public PlatformTransactionManager transactionManager(@Qualifier("multipleDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
