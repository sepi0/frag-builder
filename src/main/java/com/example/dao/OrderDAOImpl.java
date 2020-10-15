package com.example.dao;

import com.example.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public Order save(Order order) {
        entityManager.createNativeQuery("INSERT INTO orders (order_id, components,  email, phone, name) VALUES(?, ?, ?, ?, ?)")
                .setParameter(1, order.getOrderId())
                .setParameter(2, order.getComponents())
                .setParameter(3, order.getEmail())
                .setParameter(4, order.getPhone())
                .setParameter(5, order.getName())
                .executeUpdate();
        return order;
    }
}
