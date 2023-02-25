package com.whiz.clients.clientservice.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.time.*;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String names;
    private String lastname;
    private Integer age;
    private LocalDate birthdate;
}
