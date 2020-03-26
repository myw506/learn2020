package com.cloud.iot.dao;

import com.cloud.iot.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    //新建订单
    void create(Order order);

    //修改订单状态
    void update(@Param("userId") Long userId, @Param("status") Integer status);

    //根据id查看订单
    Order getOrderById(@Param("id") Long id);
}
