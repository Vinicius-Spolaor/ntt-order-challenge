package com.desafio.order.model;

import com.desafio.order.enumeration.OrderStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "orders")
@Getter
@Setter
@Builder
public class Order {
    @Id
    private String id;

    @Indexed(unique = true)
    private String orderId;

    private List<Product> products;
    private BigDecimal totalValue;
    private OrderStatusEnum status;
}
