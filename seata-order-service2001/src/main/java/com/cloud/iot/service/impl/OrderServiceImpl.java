package com.cloud.iot.service.impl;

import com.cloud.iot.dao.OrderDao;
import com.cloud.iot.domain.Order;
import com.cloud.iot.service.AccountService;
import com.cloud.iot.service.OrderService;
import com.cloud.iot.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private AccountService accountService;

    @Resource
    private StorageService storageService;

    @Override
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("------->开始新建订单");
        //1 新建订单
        orderDao.create(order);

        log.info("------->订单微服务开始调用库存,做扣减");
        //2 扣减库存
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("------->订单微服务开始调用库存,做扣减end");

        log.info("------->订单微服务开始调用账户，做扣减");
        //3 扣减账户
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("------->订单微服务开始调用账户，做扣减end");

        //4 修改订单状态，从0到1，1代表订单完成
        log.info("-------->修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("-------->修改订单状态结束");

        log.info("-------->下订单结束，@@@@");
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDao.getOrderById(id);
    }
}
