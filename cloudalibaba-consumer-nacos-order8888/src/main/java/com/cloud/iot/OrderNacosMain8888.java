package com.cloud.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderNacosMain8888 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain8888.class, args);
    }
}
