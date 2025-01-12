package com.desafio.order.dto;

import lombok.Setter;

@Setter
public class ErrorDTO {
    private int code;
    private String message;
    private String reason;
}
