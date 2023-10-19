package com.example.apispringangular.repository;

import com.example.apispringangular.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Cliente, Long> {


}
