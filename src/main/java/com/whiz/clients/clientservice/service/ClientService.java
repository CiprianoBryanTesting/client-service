package com.whiz.clients.clientservice.service;


import com.whiz.clients.clientservice.controller.dto.ClientDTO;

import java.util.*;

public interface ClientService {
    ClientDTO save(ClientDTO clientDTO);
    List<ClientDTO> getAll();
    ClientDTO update(Integer id, ClientDTO clientDTO);
    void delete(Integer id);
}
