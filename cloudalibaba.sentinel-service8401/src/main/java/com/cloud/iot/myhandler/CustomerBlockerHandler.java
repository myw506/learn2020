package com.cloud.iot.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.iot.entities.CommonResult;
import com.cloud.iot.entities.Payment;

public class CustomerBlockerHandler {
  public static CommonResult handlerException1(BlockException exception){
    return new CommonResult(444,"按客户自定义，global handlerException --------1");
  }

  public static CommonResult handlerException2(BlockException exception){
    return new CommonResult(444,"按客户自定义，global handlerException --------2");
  }
}
