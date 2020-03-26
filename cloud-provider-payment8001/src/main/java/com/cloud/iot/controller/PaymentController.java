package com.cloud.iot.controller;

import com.cloud.iot.entities.CommonResult;
import com.cloud.iot.entities.Payment;
import com.cloud.iot.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果111：" + result);

        if (result > 0){
            return new CommonResult(200,"插入成功: " + serverPort, result);
        }else {
            return new CommonResult(444,"插入失败: " + serverPort, null);
        }

    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("插入结果：" + payment);

        if (null != payment){
            return new CommonResult(200,"查询成功,serverport:"+ serverPort, payment);
        }else {
            return new CommonResult(444,"没有对应记录,serverPort:"+ serverPort+ "，查询id："+id, null);
        }

    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> list = discoveryClient.getServices();

        for(String element : list){
            log.info("services : " + element);
        }
        List<ServiceInstance> instanceList =discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance : instanceList){
            log.info("ServiceInstance : " + instance.getServiceId() +"\t" + instance.getHost()
            +"\t"+instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentByLB(){

        return "8001";
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){

        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "ht, i am paymentzipkin server fall back, welcome @_@";
    }

}
