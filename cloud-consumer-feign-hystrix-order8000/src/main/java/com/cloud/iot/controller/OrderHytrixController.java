package com.cloud.iot.controller;

import com.cloud.iot.service.PaymentHytrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//全局降级
@DefaultProperties(defaultFallback = "paymentInfo_Global_TimeOutFallbackHandler")
public class OrderHytrixController {
    @Resource
    private PaymentHytrixService paymentHytrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id){
        String result = paymentHytrixService.paymentInfo_OK(id);
        return result;
    }

    //超时或者是报错都进行降级处理
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutFallbackHandler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds" ,value = "1500")
    })
    public String paymentInfo_Timeout(@PathVariable("id") Long id){
        int i = 10/0;
        String result = paymentHytrixService.paymentInfo_Timeout(id);
        return result;
    }

    public String paymentInfo_TimeOutFallbackHandler(Long id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_TimeOutHandler, id: "+id+"\t"+"服务超时 耗时1.5秒钟";
    }

    //超时或者是报错都进行降级处理
    @GetMapping(value = "/consumer/payment/hystrix/global/timeout/{id}")
    @HystrixCommand
    public String paymentInfo_Global_Timeout(@PathVariable("id") Long id){
        int i = 10/0;
        String result = paymentHytrixService.paymentInfo_Timeout(id);
        return result;
    }

    //global fallbak全局降级
    public String paymentInfo_Global_TimeOutFallbackHandler(){
        return "Gloabl异常处理信息，请稍后再试！";
    }
}
