package com.cqgc.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * @author fengzongfu
 */
public class ServletConfig extends AbstractDispatcherServletInitializer {
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(SpringMvcConfig.class);
        return ac;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
        //“/”，可以拦截除了jsp以外所有的资源的请求，拦截这些请求的目的是交给springmvc去处理
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(SpringConfig.class);
        return ac;
    }

    //配置springmvc框架提供的处理post请求中文乱码过滤器
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("utf-8");
        return new Filter[]{characterEncodingFilter};
    }
}
