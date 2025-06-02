package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Cliente;
import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Buscar por ID (ya incluido por JpaRepository, pero lo ponemos explícito)
    Optional<Cliente> findById(Integer id);

    // Buscar por RUT
    Cliente findByRut(String rut);

    // Verificar si un correo ya existe
    boolean existsByCorreo(String correo);

    
}