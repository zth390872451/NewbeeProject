package com.newbee.net.utils;


import com.newbee.net.constant.ApplicationConstant;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ApplicationSupport implements DisposableBean, ApplicationContextAware {

    private static ApplicationContext appContext;

    public static String getValue(String key) {
        String propertyValue = appContext.getEnvironment().getProperty(key);
        return propertyValue;
    }

    private static boolean hasProfile(String profile) {
        if (!appContext.getEnvironment().getProperty(ApplicationConstant.SPRING_PROFILE_ACTIVE).equals(profile)) {
            return false;
        }
        return true;
    }


    public static Object getBean(String name) {
        return appContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return appContext.getBean(clazz);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        return appContext.getBeansOfType(type);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        appContext = null;
    }
}