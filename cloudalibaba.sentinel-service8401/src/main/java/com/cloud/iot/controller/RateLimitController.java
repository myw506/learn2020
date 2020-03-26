package com.cloud.iot.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.iot.entities.CommonResult;
import com.cloud.iot.entities.Payment;
import com.cloud.iot.myhandler.CustomerBlockerHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

  @GetMapping("/byResource")
  @SentinelResource(value = "byResource", blockHandler = "handleException")
  public CommonResult byResource(){

    return new CommonResult(200,"按资源名限流测试ok", new Payment(2020L,"serial001"));
  }


  public CommonResult handleException(BlockException exception){
    return  new CommonResult(444,exception.getClass().getCanonicalName() + "\t 服务不可用");
  }

  @GetMapping("/rateLimit/byUrl")
  @SentinelResource(value = "byUrl")
  public CommonResult byUrl(){

    return new CommonResult(200,"按url限流测试ok", new Payment(2020L,"serial002"));
  }


  @GetMapping("/rateLimit/customerBlockerHandler")
  @SentinelResource(value = "customerBlockerHandler", blockHandlerClass = CustomerBlockerHandler.class, blockHandler = "handlerException2")
  public CommonResult customerBlockerHandler(){

    return new CommonResult(200,"自定义customerBlockerHandler", new Payment(2020L,"serial003"));
  }

}
