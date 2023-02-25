package com.whiz.clients.clientservice.repository;

import com.whiz.clients.clientservice.repository.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
