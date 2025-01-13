package com.desafio.order.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorDTO {
    private int code;
    private String message;
    private String reason;
}
