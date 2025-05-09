package com.flyaway.project.flyaway.repository;

import com.flyaway.project.flyaway.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Encontrar clientes por:
    // Nombres
    @Query(value = "SELECT * FROM Cliente WHERE nombres= :nombre", nativeQuery = true)
    Cliente findbyNombre(@Param("nombres") String nombre);

    // Apellidos
    @Query(value = "SELECT * FROM Cliente WHERE apellidos= :apellido", nativeQuery = true)
    Cliente findbyApellido(@Param("nombres") String apellido);

    // Correo
    @Query(value = "SELECT * FROM Cliente WHERE correo = :correo", nativeQuery = true)
    Cliente findbyCorreo(@Param("correo") String correo);
}
