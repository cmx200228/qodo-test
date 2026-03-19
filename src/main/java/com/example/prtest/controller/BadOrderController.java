package com.example.prtest.controller;

import com.example.prtest.dto.OrderRequest;
import com.example.prtest.dto.OrderResponse;
import com.example.prtest.entity.Order;
import com.example.prtest.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bad/orders")
public class BadOrderController {

    private final OrderRepository orderRepository;

    public BadOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    @Transactional // Violation: Transaction demarcation on controller method
    public ResponseEntity<OrderResponse> createOrderDirectly(@RequestBody OrderRequest request) {
        // Violation: New domain objects being constructed entirely within controller
        Order order = new Order();
        order.setStatus("PROCESSING");
        
        // Violation: Condition-heavy / pricing rules / algorithmic code in controller
        double price = 100.0;
        double total = 0.0;
        
        if (request.getQuantity() != null && request.getQuantity() > 0) {
            if (request.getQuantity() >= 10) {
                total = request.getQuantity() * price * 0.8; // Custom volume discount
            } else if (request.getQuantity() >= 5) {
                total = request.getQuantity() * price * 0.9;
            } else {
                total = request.getQuantity() * price;
            }
        }
        order.setTotalAmount(total);

        // Violation: Direct calls to Repository/EntityManager equivalent
        orderRepository.save(order);

        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setStatus(order.getStatus());

        return ResponseEntity.ok(response);
    }
}
