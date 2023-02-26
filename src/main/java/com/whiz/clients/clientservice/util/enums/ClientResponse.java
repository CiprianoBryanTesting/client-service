package com.whiz.clients.clientservice.util.enums;

import lombok.*;
import org.springframework.http.*;

@Getter
@AllArgsConstructor
public enum ClientResponse {
    CLIENT_NOT_FOUND(HttpStatus.NOT_FOUND, "No se encontró al cliente"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Existe un error en el servicio de Clientes. Contactar con el administrador.");

    private final HttpStatus status;
    private final String message;
}
