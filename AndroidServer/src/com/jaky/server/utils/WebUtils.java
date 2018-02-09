package com.jaky.server.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.UUID;
import org.apache.commons.beanutils.BeanUtils;

public class WebUtils {
    //把request对象中的请求参数封装到formbean中
    public static <T> T request2Bean(HttpServletRequest request, Class<T> clazz) {
        try {
            T bean = clazz.newInstance();
            Enumeration<String> e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String name = e.nextElement();
                String value = request.getParameter(name);
                BeanUtils.setProperty(bean, name, value);
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String makeID() {
        //UUID算法，可算出世界上唯一的128bit的UUID，toString()生成36位长的字符串。
        return UUID.randomUUID().toString();
    }
}
