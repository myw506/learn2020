package com.cloud.iot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
/**
 * 必须发送 curl -X POST "http://localhost:3355/actuator/refresh"请求通知客户端才能刷新配置（不带消息总线模式）
 *
 * 消息总线模式下，使用rabbitmq监听更新消息
 * curl -X POST "http://localhost:3344/actuator/bus-refresh"
 */
public class ConfigClientController {
    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("configInfo")
    public String configInfo(){
        return "serverPort:" + serverPort +"\t\n\n configInfo: " + configInfo;
    }
}
