package com.jkh9610.spring02.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration  // 스프링부트를 위한
@MapperScan(basePackages = {"com.jkh9610.spring02.mapper"})     // MyBatis를 위한
// 해당패키지에서 Mapper로 인식하라
public class MyBatisConfig {    
    
}
