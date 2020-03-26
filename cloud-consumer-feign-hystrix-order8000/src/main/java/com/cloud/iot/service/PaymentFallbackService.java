package com.cloud.iot.service;

import org.springframework.stereotype.Component;

//openFeign的服务降级处理，实现结偶
@Component
public class PaymentFallbackService implements PaymentHytrixService{
    @Override
    public String paymentInfo_OK(Long id) {
        return "-----PaymentFallbackService fall back,o(T--T)o";
    }

    @Override
    public String paymentInfo_Timeout(Long id) {
        return "-----PaymentFallbackService fall back,o(T--T)o";
    }
}
