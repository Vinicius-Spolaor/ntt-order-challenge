package com.desafio.order.service;

import com.desafio.order.dto.OrderDTO;
import com.desafio.order.dto.ProductDTO;
import com.desafio.order.enumeration.OrderStatusEnum;
import com.desafio.order.exception.OrderNotReceivedException;
import com.desafio.order.model.Order;
import com.desafio.order.model.Product;
import com.desafio.order.repository.OrderRepository;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final RedisTemplate<String, Boolean> redisTemplate;

    public OrderService(OrderRepository orderRepository, RedisTemplate<String, Boolean> redisTemplate) {
        this.orderRepository = orderRepository;
        this.redisTemplate = redisTemplate;
    }

    @Async
    @Override
    public void processOrder(OrderDTO orderDTO) {
        var redisKey = "order:" + orderDTO.getOrderId();

        if (redisTemplate.hasKey(redisKey) || orderRepository.existsByOrderId(orderDTO.getOrderId())) {
            throw new AmqpRejectAndDontRequeueException("Duplicate order detected");
        }

        var products = orderDTO.getProducts()
                       .stream()
                       .map(dto -> Product.builder()
                                              .id(dto.getId())
                                              .value(dto.getValue())
                                              .quantity(dto.getQuantity())
                                              .build())
                       .toList();

        var order = Order.builder()
                  .orderId(orderDTO.getOrderId())
                  .products(products)
                  .status(OrderStatusEnum.RECEIVED)
                  .build();

        orderRepository.save(order);
        calculateTotalValue(order);
    }

    @Async
    private void calculateTotalValue(Order order) {
        var totalValue = order.getProducts().stream()
                         .map(product -> product.getValue().multiply(BigDecimal.valueOf(product.getQuantity())))
                         .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalValue(totalValue);
        order.setStatus(OrderStatusEnum.PROCESSED);
        orderRepository.save(order);
    }

    @Override
    public OrderDTO getOrderStatus(String orderId) {
        var order = orderRepository.findByOrderId(orderId)
                    .orElseThrow(() -> new OrderNotReceivedException("Order not found with ID: " + orderId));

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        var productDTO = order.getProducts().stream()
                         .map(product -> ProductDTO.builder()
                                                 .id(product.getId())
                                                 .value(product.getValue())
                                                 .quantity(product.getQuantity())
                                                 .build())
                         .toList();



        return OrderDTO.builder().orderId(order.getOrderId()).products(productDTO).build();
    }
}
