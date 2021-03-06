package com.spring.utils.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * ////////////////////////////////////////////////////////////////////
 * //                          _ooOoo_                               //
 * //                         o8888888o                              //
 * //                         88" . "88                              //
 * //                         (| ^_^ |)                              //
 * //                         O\  =  /O                              //
 * //                      ____/`---'\____                           //
 * //                    .'  \\|     |//  `.                         //
 * //                   /  \\|||  :  |||//  \                        //
 * //                  /  _||||| -:- |||||-  \                       //
 * //                  |   | \\\  -  /// |   |                       //
 * //                  | \_|  ''\---/''  |   |                       //
 * //                  \  .-\__  `-`  ___/-. /                       //
 * //                ___`. .'  /--.--\  `. . ___                     //
 * //              ."" '<  `.___\_<|>_/___.'  >'"".                  //
 * //            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
 * //            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
 * //      ========`-.____`-.___\_____/___.-`____.-'========         //
 * //                           `=---='                              //
 * //      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
 * //         ????????????           ??????BUG           ????????????             //
 * //          ??????:                                                  //
 * //                 ?????????????????????????????????????????????;                     //
 * //                 ?????????????????????????????????????????????.                     //
 * //                 ?????????????????????????????????????????????;                     //
 * //                 ?????????????????????????????????????????????.                     //
 * //                 ?????????????????????????????????????????????;                     //
 * //                 ?????????????????????????????????????????????.                     //
 * //                 ?????????????????????????????????????????????;                     //
 * //                 ??????????????????????????????????????????????                     //
 * ////////////////////////////////////////////////////////////////////
 *
 * @date : 2021/12/12 15:07
 * @author: linzhou
 * @description : SpringUtil
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    /**
     * ?????????????????????bean??????
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> getBeanList(Class<T> tClass) {
        Map<String, T> beansOfType = applicationContext.getBeansOfType(tClass);
        List<T> rlt = new ArrayList<>(beansOfType.size());
        rlt.addAll(beansOfType.values());
        rlt.sort(Comparator.comparingInt(SpringUtil::getOrder));
        return rlt;
    }

    /**
     * ?????????????????????bean
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }


    public static int getOrder(Object o) {
        Order order = AnnotationUtils.findAnnotation(o.getClass(), Order.class);
        if (order != null) {
            return order.value();
        }
        return 0;
    }

    /**
     * ??????controller??????????????????????????????,??????????????????
     *
     * @param c
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getRequestAnnotation(Class<T> c) {
        Object handler = getHandler();
        if (Objects.isNull(handler)) {
            return null;
        }
        T annotation = null;
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            annotation = method.getAnnotation(c);
            if (Objects.isNull(annotation)) {
                annotation = clazz.getAnnotation(c);
            }
        }
        return annotation;
    }

    public static Object getHandler() {
        DispatcherServlet dispatcherServlet = applicationContext.getBean(DispatcherServlet.class);
        List<HandlerMapping> handlerMappings = dispatcherServlet.getHandlerMappings();
        HttpServletRequest request = getHttpServletRequest();
        if (handlerMappings != null) {
            for (HandlerMapping mapping : handlerMappings) {
                try {
                    HandlerExecutionChain handler = mapping.getHandler(request);
                    if (handler != null) {
                        return handler.getHandler();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * ??????request
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sra.getRequest();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }
}
