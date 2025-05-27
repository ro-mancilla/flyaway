package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.servicios;

public interface ServicioRepository extends JpaRepository<servicios, Integer> {
    List<servicios> findByIdUsuario(int idUsuario);

    List<servicios> findByTipoServicio(String tipoServicio);

    List<servicios> findByCategoria(String categoria);

    List<servicios> findByDisponiblidadServicioTrue();

    List<servicios> findByProveedor(String proveedor);

}
