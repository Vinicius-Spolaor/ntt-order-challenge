package com.desafio.order.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Product {
    private Integer id;
    private BigDecimal value;
    private int quantity;
}
