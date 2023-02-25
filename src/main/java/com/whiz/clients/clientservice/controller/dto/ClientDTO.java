package com.whiz.clients.clientservice.controller.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.*;

@Data
public class ClientDTO {
    private Integer id;
    @NotNull(message = "Debe ingresar su nombre")
    private String names;
    private String lastname;
    @NotNull(message = "Debe ingresar su edad")
    private Integer age;
    @NotNull(message = "Debe ingresar su fecha de nacimiento")
    private LocalDate birthdate;
    private LocalDate deathdate;
}
