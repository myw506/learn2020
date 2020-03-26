package com.cloud.iot.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.iot.entities.CommonResult;
import com.cloud.iot.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderNacosSentinelController {

  @Resource
  private RestTemplate restTemplate;

  @Value("${service-url.nacos-user-service}")
  private String serverURL;

  @GetMapping("/consumer/fallback/{id}")
  //没有配置
//  @SentinelResource(value = "fallback")
  //fallback只负责业务异常，管理java异常
//  @SentinelResource(value = "fallback",fallback = "handleFallback")
  //支配handler，管理配置异常
//  @SentinelResource(value = "fallback", blockHandler = "blockHandler")
  //若blockHandler和fallback都配置，则被限流降级而抛出BlockException时只会进入blockHandler处理逻辑
  @SentinelResource(value = "fallback", fallback = "handleFallback", blockHandler = "blockHandler")

  public CommonResult<Payment> paymentInfo(@PathVariable("id") Long id){
    CommonResult<Payment> result = restTemplate.getForObject(serverURL + "/paymentSQL/"+id, CommonResult.class);

    if(id == 4){
      throw new IllegalArgumentException("IllegalArgumentException, 参数非法");
    }else if(result.getData() == null){
      throw new NullPointerException("NullPointerException, 该ID没有记录，空指针异常");
    }
    return result;
  }

  public CommonResult<Payment> handleFallback(@PathVariable Long id, Throwable exception){
    Payment payment = new Payment(id,"null");
    return new CommonResult<>(444, "-------handleFallback兜底处理。。。。。" + exception.getMessage(), payment);
  }

  public CommonResult<Payment> blockHandler(Long id, BlockException exception){
    Payment payment = new Payment(id,"null");
    return new CommonResult<>(445, "-------限流，无此流水。。。。。" + exception.getMessage(), payment);
  }
}
