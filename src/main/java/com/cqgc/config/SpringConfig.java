package com.cqgc.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.cqgc.aspect.TransferLogAop;

@Configuration
@ComponentScan({"com.cqgc.service","com.cqgc.dao","com.cqgc.threadLocal","com.cqgc.aspect"})
@Import({JdbcConfig.class, MybatisConfig.class,TransferLogAop.class})
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class SpringConfig {
}