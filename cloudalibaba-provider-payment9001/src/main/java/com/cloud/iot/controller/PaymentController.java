package com.cloud.iot.controller;

import com.cloud.iot.entities.CommonResult;
import com.cloud.iot.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "1111111111111111"));
        hashMap.put(2L, new Payment(2L, "2222222222222222"));
        hashMap.put(3L, new Payment(3L, "3333333333333333"));
    }

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Long id){

        return "nacos registry, serverPort: " + serverPort + "\t id" + id;
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        log.info("PathVariable id :" + id);
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200, " from mysql, serverPort : " + serverPort, payment);
        return result;
    }

}
