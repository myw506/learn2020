package com.cloud.iot.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cloud.iot.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_Ok(Long id) {
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_ok, id: "+id+"\t"+"@_@**";
    }

    //程序异常或者超时都将降级处理
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds" ,value = "3000")
    })
    public String paymentInfo_Timeout(Long id) {
//        int i = 10/0;

        try{
            TimeUnit.SECONDS.sleep(50);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_ok, id: "+id+"\t"+"@_@** 耗时5秒钟";
    }

    public String paymentInfo_TimeOutHandler(Long id){

        return "线程池："+Thread.currentThread().getName()+" paymentInfo_TimeOutHandler, id: "+id+"\t"+"服务超时 耗时5秒钟";
    }


    //==服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "60"),//失败率达到多少后跳闸
    })
    @Override
    public String paymentCircuitBreaker(@PathVariable("id") Long id){
        if(id < 0){
            throw new RuntimeException("****id不能为负数");
        }

        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号:" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Long id){
        return "id不能为负数，请稍后再试。。。。 id:" + id ;
    }
}
