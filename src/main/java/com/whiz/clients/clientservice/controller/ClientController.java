package com.whiz.clients.clientservice.controller;

import com.whiz.clients.clientservice.controller.dto.ClientDTO;
import com.whiz.clients.clientservice.service.*;
import com.whiz.clients.clientservice.util.enums.*;
import io.github.resilience4j.circuitbreaker.annotation.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*")
@Slf4j
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.save(clientDTO));
    }

    @GetMapping
    @CircuitBreaker(name = "get-all", fallbackMethod = "apiFallBackGetAll")
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

    public ResponseEntity<?> apiFallBackGetAll(Exception exception) {
        log.info(ClientResponse.INTERNAL_SERVER_ERROR.getMessage() + ", Error={}", exception.getMessage());
        return ResponseEntity.noContent().build();
    }
}
