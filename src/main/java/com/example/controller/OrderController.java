package com.example.controller;

import com.example.model.Order;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @CrossOrigin(origins = "https://sepi0.github.io/frag-builder-frontend/")
    @PostMapping(path = "/send")
    public Order newOrder(@RequestBody Order order) {
        orderService.save(order);
        return order;
    }
}
