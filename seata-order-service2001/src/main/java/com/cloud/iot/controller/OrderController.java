package com.cloud.iot.controller;

import com.cloud.iot.domain.CommonResult;
import com.cloud.iot.domain.Order;
import com.cloud.iot.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);

        return new CommonResult(200,"订单创建成功");
    }

    @GetMapping("/order/get/{id}")
    public CommonResult<Order> getOrderById(@PathVariable("id") Long id){
        Order order = orderService.getOrderById(id);
        return new CommonResult(200,"查询成功", order);
    }
}
