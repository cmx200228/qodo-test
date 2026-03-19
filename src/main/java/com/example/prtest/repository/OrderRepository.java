package com.example.prtest.repository;

import com.example.prtest.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    public void save(Order order) {
        // Mock save logic
        order.setId(System.currentTimeMillis());
    }
}
