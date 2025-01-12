package com.desafio.order.service;

import com.desafio.order.dto.OrderDTO;

public interface IOrderService {
    void processOrder(OrderDTO orderDTO);
    OrderDTO getOrderStatus(String orderId);
}
