package com.cloud.iot.service;

public interface PaymentService {

    public String paymentInfo_Ok(Long id);

    public String paymentInfo_Timeout(Long id);

    public String paymentCircuitBreaker(Long id);
}
