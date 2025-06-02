package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findById(Integer id);
    // Buscar por RUT
    Cliente findByRut(String rut);
    
    // Verificar si un correo ya existe
    boolean existsByCorreo(String correo);
    
    
}
