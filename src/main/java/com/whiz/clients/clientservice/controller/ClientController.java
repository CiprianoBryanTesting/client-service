package com.whiz.clients.clientservice.controller;

import com.whiz.clients.clientservice.controller.dto.ClientDTO;
import com.whiz.clients.clientservice.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.save(clientDTO));
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAll() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Integer id, @Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.update(id, clientDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
