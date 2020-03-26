package com.cloud.iot.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 代码方式实现路由转发
 */
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){

        RouteLocatorBuilder.Builder routers = routeLocatorBuilder.routes();
        routers.route("path_route_guonei",
                r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();

        routers.route("path_route_guoji",
                r -> r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
        return routers.build();
    }
}
