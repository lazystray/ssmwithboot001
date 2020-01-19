package com.zyl.service;

import com.zyl.po.OrderEntity;

public interface OrderService {

    OrderEntity queryOrderById(Long id);

    void saveOrder(OrderEntity orderEntity);

    void updateOrder(OrderEntity entity);

    void deleteOrderById(Long id);
}
