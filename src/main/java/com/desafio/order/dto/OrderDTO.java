package com.desafio.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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
}
