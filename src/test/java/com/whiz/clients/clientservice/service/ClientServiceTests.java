package com.whiz.clients.clientservice.service;

import com.whiz.clients.clientservice.config.exception.*;
import com.whiz.clients.clientservice.controller.dto.*;
import com.whiz.clients.clientservice.repository.*;
import com.whiz.clients.clientservice.repository.entity.*;
import com.whiz.clients.clientservice.service.impl.*;
import com.whiz.clients.clientservice.util.mapper.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.time.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTests {
    private ClientService clientService;
    @Mock
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    @BeforeEach
    void setUp() {
        clientMapper = new ClientMapperImpl();
        clientService = new ClientServiceImpl(clientRepository, clientMapper);
    }

    @Test
    void saveSuccessfully() {
        ClientDTO clientDTO = getClientDTO();

        Client client = clientMapper.toEntity(clientDTO);
        Mockito.when(clientRepository.save(client)).thenReturn(getClient());

        ClientDTO clientDTOSaved = clientService.save(clientDTO);
        Assertions.assertEquals(1, clientDTOSaved.getId());
    }

    @Test
    void saveFailure() {
        ClientDTO clientDTO = getClientDTO();
        Client client = clientMapper.toEntity(clientDTO);

        Mockito.when(clientRepository.save(client)).thenReturn(null);

        ClientDTO clientDTOSaved = clientService.save(clientDTO);
        Assertions.assertNull(clientDTOSaved);
    }

    @Test
    void updateSuccessfully() {
        ClientDTO clientDTO = getClientDTO();

        Mockito.when(clientRepository.existsById(1)).thenReturn(Boolean.TRUE);
        Mockito.when(clientRepository.save(getClient())).thenReturn(getClient());

        ClientDTO clientDTOUpdated = clientService.update(1, clientDTO);
        Assertions.assertEquals(1, clientDTOUpdated.getId());
    }

    @Test
    void updateFailure() {
        Mockito.when(clientRepository.existsById(2)).thenReturn(Boolean.FALSE);
        Assertions.assertThrows(BusinessException.class, () -> clientService.update(2, getClientDTO()));
    }

    @Test
    void deleteSuccessfully() {
        Mockito.when(clientRepository.existsById(3)).thenReturn(Boolean.TRUE);
        Mockito.doNothing().when(clientRepository).deleteById(3);

        clientService.delete(3);
    }

    @Test
    void deleteFailure() {
        Mockito.when(clientRepository.existsById(4)).thenReturn(Boolean.FALSE);
        Assertions.assertThrows(BusinessException.class, () -> clientService.delete(4));
    }

    private ClientDTO getClientDTO() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNames("MARIO ALEJANDRO");
        clientDTO.setLastname("GOMEZ SILVA");
        clientDTO.setAge(23);
        clientDTO.setBirthdate(LocalDate.parse("2023-02-24"));
        return clientDTO;
    }

    private Client getClient() {
        Client client = new Client();
        client.setId(1);
        client.setNames("MARIO ALEJANDRO");
        client.setLastname("GOMEZ SILVA");
        client.setAge(23);
        client.setBirthdate(LocalDate.parse("2023-02-24"));
        return client;
    }
}
