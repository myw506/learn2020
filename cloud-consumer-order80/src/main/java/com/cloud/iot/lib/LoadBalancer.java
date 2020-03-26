package com.cloud.iot.lib;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 自定义负载均衡接口
 */
public interface LoadBalancer {
    ServiceInstance intances(List<ServiceInstance> serviceInstances);
}
