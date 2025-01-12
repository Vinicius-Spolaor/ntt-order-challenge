package com.desafio.order.component;

import com.desafio.order.dto.OrderDTO;
import com.desafio.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderMessageListenerComponent {
    private final OrderService orderService;

    public OrderMessageListenerComponent(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = "orderQueue")
    public void receiveOrder(OrderDTO orderDTO) {
        orderService.processOrder(orderDTO);
    }
}
