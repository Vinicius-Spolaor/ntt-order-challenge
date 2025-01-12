package com.desafio.order.controller;

import com.desafio.order.dto.OrderDTO;
import com.desafio.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/status/{orderId}")
    public ResponseEntity<OrderDTO> getOrderStatus(@PathVariable String orderId) {
        var order = orderService.getOrderStatus(orderId);
        return ResponseEntity.ok().body(order);
    }
}
