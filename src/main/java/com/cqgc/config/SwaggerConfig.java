package com.cqgc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 配置类
 */
@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig {


    @Bean
    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("SSM-Book API 文档")
                .version("2.0")
                .description("图书管理系统接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cqgc.controller"))
                .paths(PathSelectors.any())
                .build();
        // 忽略异常类，避免 Swagger 扫描异常类
        docket.ignoredParameterTypes(
java.lang.Throwable.class,
            java.lang.Exception.class,
            java.lang.RuntimeException.class
        );
        return docket;
    }
}

