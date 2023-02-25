package com.whiz.clients.clientservice.config.exception;

import com.whiz.clients.clientservice.util.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;
    private final LocalDateTime date;

    public BusinessException(ClientResponse response) {
        this.httpStatus = response.getStatus();
        this.message = response.getMessage();
        this.date = LocalDateTime.now();
    }
}
