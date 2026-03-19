package com.example.prtest.controller;

import com.example.prtest.dto.OrderRequest;
import com.example.prtest.dto.OrderResponse;
import com.example.prtest.entity.Order;
import com.example.prtest.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/good/orders")
public class GoodOrderController {

    private final OrderService orderService;

    public GoodOrderController(OrderService orderService) {
        this.orderService = orderService;
        // The business logic is delegated to OrderService correctly
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
        // Only request mapping, basic input validation, and delegation to service.
        Order order = orderService.createOrder(request);
        
        // Response mapping / DTO conversion
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setStatus(order.getStatus());
        
        return ResponseEntity.ok(response);
    }
}
