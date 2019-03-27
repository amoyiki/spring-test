package com.amoyiki.springtest.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author amoyiki
 * @since 2019/3/27
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }

    }

    /**
     * 获取 appliaction
     *
     * @param
     * @return org.springframework.context.ApplicationContext
     * @author amoyiki
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过 name 获取 Bean
     *
     * @param name
     * @return java.lang.Object
     * @author amoyiki
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }


    /**
     * 通过 class 获取 Bean
     *
     * @param clazz
     * @return T
     * @author amoyiki
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过 name 和 class 定位 Bean
     *
     * @param name
     * @param clazz
     * @return T
     * @author amoyiki
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
