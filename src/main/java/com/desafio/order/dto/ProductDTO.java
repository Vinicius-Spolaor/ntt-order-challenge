package com.desafio.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representação de um produto")
public class ProductDTO {

    @Schema(description = "Identificador único do produto", example = "123456")
    private Integer id;

    @Schema(description = "Valor do produto", example = "12,36")
    private BigDecimal value;

    @Schema(description = "Quantidade do produto", example = "2")
    private int quantity;
}
