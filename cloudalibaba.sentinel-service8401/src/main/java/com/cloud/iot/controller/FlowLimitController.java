package com.cloud.iot.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.iot.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
//        try{
//            TimeUnit.SECONDS.sleep(1);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        log.info(Thread.currentThread().getId() + " Thread Name :" + Thread.currentThread().getName());
        return Thread.currentThread().getId() + " Thread Name :" + Thread.currentThread().getName() + "------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getId() + " Thread Name :" + Thread.currentThread().getName());

        return Thread.currentThread().getId() + " Thread Name :" + Thread.currentThread().getName() + "------testB";

    }

    @GetMapping("/testD")
    public String testD(){
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getId() + " Thread Name :" + Thread.currentThread().getName());
        return Thread.currentThread().getId() + " Thread Name :" + Thread.currentThread().getName() + "------testD";
    }

    @GetMapping("/testE")
    public String testE(){

        log.info(Thread.currentThread().getId() + " Thread Name :" + Thread.currentThread().getName()+"异常比例");
        int a = 10/0;
        return Thread.currentThread().getId() + " Thread Name :" + Thread.currentThread().getName() + "------testD";
    }


    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String hotKey(@RequestParam(value = "p1", required = false) String p1, @RequestParam(value = "p2", required = false) String p2){
        return "testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "-------deal_testHotKey。。。。。";
    }

}
