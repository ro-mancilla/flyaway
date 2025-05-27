package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Vuelo;


public interface VueloRepository extends JpaRepository <Vuelo, Integer> { 
    // Buscar todos los vuelos de un usuario
    List<Vuelo> findByIdUsuario(Integer idUsuario);

    // Buscar vuelos por categoría (por ejemplo, internacional, nacional)
    List<Vuelo> findByCategoria(String categoria);

    // Buscar vuelos disponibles
    List<Vuelo> findByDisponibilidadServicioTrue();

    // Buscar vuelos entre fechas
    List<Vuelo> findByFechaInicioBetween(java.sql.Date inicio, java.sql.Date fin);
    

}
