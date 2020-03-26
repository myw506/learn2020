package com.cloud.iot.service;

import com.cloud.iot.domain.Order;

public interface OrderService {
    void create(Order order);

    Order getOrderById(Long id);
}
