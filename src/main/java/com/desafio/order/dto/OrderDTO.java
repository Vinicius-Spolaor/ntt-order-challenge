package com.desafio.order.dto;

import com.desafio.order.enumeration.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representação de um pedido")
public class OrderDTO {

    @Schema(description = "Identificador único do pedido", example = "123456")
    private String orderId;

    @Schema(description = "Lista de produtos no pedido")
    private List<ProductDTO> products;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal totalValue;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OrderStatusEnum status;
}
