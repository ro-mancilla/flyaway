package com.flyaway.project.flyaway.repository;

import com.flyaway.project.flyaway.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Long> {

    // Encontrar clientes por:
    // Nombres
    List<Cliente> findByNombres(String nombres);

    // Apellidos
    List<Cliente> findByApellidos(String apellidos);

    // Ambos
    List<Cliente> findByNombresApellidos(String nombres, String apellidos);
}
