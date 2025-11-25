package com.cqgc.config;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

public class MybatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setTypeAliasesPackage("com.cqgc.domain");
        sqlSessionFactoryBean.setDataSource(dataSource);
        
        // 设置XML映射文件的位置
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com/cqgc/mapper/*.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Configuration configuration = new Configuration();
        //设置整合mybatis驼峰命名映射
        configuration.setMapUnderscoreToCamelCase(true);
        //设置打印日志
        configuration.setLogImpl(StdOutImpl.class);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.cqgc.dao");
        return mapperScannerConfigurer;
    }
}