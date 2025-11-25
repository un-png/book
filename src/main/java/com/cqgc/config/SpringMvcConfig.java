package com.cqgc.config;

import com.cqgc.interceptor.LoginInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springmvc 配置类
 */
@Configuration
@ComponentScan({"com.cqgc.controller", "com.cqgc.handler", "com.cqgc.exception"})
@EnableWebMvc //这个注解有很多功能，这里暂时使用里面json与java对象自动转换
@Import(SwaggerConfig.class) // 导入 Swagger 配置类
public class SpringMvcConfig implements WebMvcConfigurer {

    //配置视图解析器，配置视图前缀与后缀
//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver(){
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        //配置前缀
//        viewResolver.setPrefix("/pages/");
//        //配置后缀
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登录拦截器，用于从请求头或Session中获取用户ID并设置到ThreadLocal
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")  // 拦截所有请求
                .excludePathPatterns("/user/login", "/pages/**", "/js/**", "/css/**", "/plugins/**", 
                                     "/doc.html", "/swagger-ui.html", "/webjars/**", "/v2/api-docs/**",
                                     "/swagger-resources/**");  // 排除登录接口和静态资源
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
        // 添加 Knife4j 的静态资源映射
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/doc.html");
        registry.addResourceHandler("/*.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

}