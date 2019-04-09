package com.amoyiki.springtest.config;

/**
 * 设置，获取，清除当前线程内数据源
 *
 * @author amoyiki
 * @date 2019/4/9
 */
public class DataSourceContextHolder {
	private static final ThreadLocal<String> THREAD_LOCAL = new InheritableThreadLocal<>();

	/**
	 * 设置数据源
	 *
	 * @param db
	 * @return void
	 * @author amoyiki
	 */
	public static void setDateSource(String db) {
		THREAD_LOCAL.set(db);
	}

	/**
	 * 获取当前数据源
	 *
	 * @param
	 * @return java.lang.String
	 * @author amoyiki
	 */
	public static String getDataSource() {
		return THREAD_LOCAL.get();
	}

	/**
	 * 清除上下文数据
	 *
	 * @param
	 * @return void
	 * @author amoyiki
	 */
	public static void clear() {
		THREAD_LOCAL.remove();
	}
}
