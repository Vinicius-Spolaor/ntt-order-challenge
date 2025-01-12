package com.desafio.order.exception;

public class OrderNotReceivedException extends RuntimeException {

    public OrderNotReceivedException(String message) {
        super(message);
    }
}
