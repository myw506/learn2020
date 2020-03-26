package com.cloud.iot.service.impl;

import com.cloud.iot.dao.PaymentDao;
import com.cloud.iot.entities.Payment;
import com.cloud.iot.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(long id){
        return paymentDao.getPaymentById(id);
    }
}
