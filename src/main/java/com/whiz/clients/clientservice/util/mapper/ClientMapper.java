package com.whiz.clients.clientservice.util.mapper;

import com.whiz.clients.clientservice.controller.dto.ClientDTO;
import com.whiz.clients.clientservice.repository.entity.Client;
import org.mapstruct.Mapper;

import java.util.*;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientDTO clientDTO);
    ClientDTO toDTO(Client client);
    List<ClientDTO> toDTO(List<Client> client);
}
