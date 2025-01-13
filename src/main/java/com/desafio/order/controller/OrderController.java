package com.desafio.order.controller;

import com.desafio.order.dto.OrderDTO;
import com.desafio.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Gerenciamento de Pedidos")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/status/{orderId}")
    @Operation(
        summary = "Obter o status de um pedido",
        description = "Retorna o status de um pedido pelo ID informado junto com suas informações.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
        }
    )
    public ResponseEntity<OrderDTO> getOrderStatus(@PathVariable String orderId) {
        var order = orderService.getOrderStatus(orderId);
        return ResponseEntity.ok().body(order);
    }
}
