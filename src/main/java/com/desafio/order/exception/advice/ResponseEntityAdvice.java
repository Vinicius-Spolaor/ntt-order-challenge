package com.desafio.order.exception.advice;

import com.desafio.order.dto.ErrorDTO;
import com.desafio.order.exception.OrderNotReceivedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseEntityAdvice {

    @ExceptionHandler({ OrderNotReceivedException.class })
    protected ResponseEntity<?> handleOrderNotReceivedException(OrderNotReceivedException ex) {
        var errorDTO = new ErrorDTO();
        errorDTO.setCode(HttpStatus.BAD_REQUEST.value());
        errorDTO.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorDTO.setReason(ex.getMessage());
        return ResponseEntity.badRequest().body(errorDTO);
    }

}
