package com.cloud.iot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.cloud.iot.dao"})
public class MyBatisConfig {
}
