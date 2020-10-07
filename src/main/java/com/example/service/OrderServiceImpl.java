package com.example.service;

import com.example.dao.OrderDAO;
import com.example.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Transactional
    @Override
    public Order save(Order order) {
        orderDAO.save(order);
        return order;
    }
}
