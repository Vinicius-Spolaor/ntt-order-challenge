package com.desafio.order.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
@Getter
@Setter
@Builder
public class Product {
    @Id
    private Integer id;
    private BigDecimal value;
    private int quantity;
}
