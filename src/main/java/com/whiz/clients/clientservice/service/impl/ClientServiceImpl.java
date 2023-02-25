package com.whiz.clients.clientservice.service.impl;

import com.whiz.clients.clientservice.config.exception.*;
import com.whiz.clients.clientservice.controller.dto.ClientDTO;
import com.whiz.clients.clientservice.repository.ClientRepository;
import com.whiz.clients.clientservice.repository.entity.Client;
import com.whiz.clients.clientservice.service.ClientService;
import com.whiz.clients.clientservice.util.enums.*;
import com.whiz.clients.clientservice.util.mapper.ClientMapper;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

import javax.transaction.*;
import java.time.*;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Value("${life.expectancy}")
    private Double lifeExpentancy;

    @Transactional
    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public List<ClientDTO> getAll() {
        List<ClientDTO> clientDTOS = clientMapper.toDTO(clientRepository.findAll());
        clientDTOS.stream().forEach(clientDTO -> clientDTO.setDeathdate(calculateProbableDeathdate(clientDTO.getBirthdate())));
        return clientDTOS;
    }

    @Transactional
    @Override
    public ClientDTO update(Integer id, ClientDTO clientDTO) {
        if (!clientRepository.existsById(id)) {
            log.info(ClientResponse.CLIENT_NOT_FOUND.getMessage());
            throw new BusinessException(ClientResponse.CLIENT_NOT_FOUND);
        }
        clientDTO.setId(id);
        Client client = clientMapper.toEntity(clientDTO);
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        if (!clientRepository.existsById(id)) {
            log.info(ClientResponse.CLIENT_NOT_FOUND.getMessage());
            throw new BusinessException(ClientResponse.CLIENT_NOT_FOUND);
        }
        clientRepository.deleteById(id);
    }

    private LocalDate calculateProbableDeathdate(LocalDate birthdate) {
        return birthdate.plusMonths((long) (lifeExpentancy*12));
    }
}
