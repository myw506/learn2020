package com.cloud.iot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope //开启自动刷新
/**
 * 必须发送 curl -X POST "http://localhost:3355/actuator/refresh"请求通知客户端才能刷新配置（不带消息总线模式）
 *
 * 消息总线模式下，使用rabbitmq监听更新消息
 * curl -X POST "http://localhost:3344/actuator/bus-refresh" （全部通知）
 *
 * curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355" （指定通知）通知3355而不通知3366
 */
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return  configInfo;
    }
}
