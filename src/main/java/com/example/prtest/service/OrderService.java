package com.example.prtest.service;

import com.example.prtest.dto.OrderRequest;
import com.example.prtest.entity.Order;
import com.example.prtest.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    @Transactional
    public Order createOrder(OrderRequest request) {
        Order order = new Order();
        order.setStatus("CREATED");
        order.setTotalAmount(calculateTotal(request));
        orderRepository.save(order);
        return order;
    }

    private Double calculateTotal(OrderRequest request) {
        if (request.getQuantity() != null && request.getQuantity() > 10) {
            return request.getQuantity() * 100.0 * 0.9; // 10% discount
        }
        return (request.getQuantity() != null ? request.getQuantity() : 0) * 100.0;
    }
}
