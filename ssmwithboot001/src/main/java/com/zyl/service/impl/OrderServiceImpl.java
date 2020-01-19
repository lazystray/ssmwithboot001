package com.zyl.service.impl;

import com.zyl.po.OrderEntity;
import com.zyl.service.OrderService;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
    @Override
    public OrderEntity queryOrderById(Long id) {
        System.out.println("id===="+id);
        return null;
    }

    @Override
    public void saveOrder(OrderEntity orderEntity) {

    }

    @Override
    public void updateOrder(OrderEntity entity) {

    }

    @Override
    public void deleteOrderById(Long id) {

    }
}
