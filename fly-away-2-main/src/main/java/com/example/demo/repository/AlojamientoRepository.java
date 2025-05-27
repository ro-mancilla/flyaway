package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Alojamiento;

public interface AlojamientoRepository extends JpaRepository<Alojamiento, Integer> {
    
    // Buscar alojamientos por proveedor (bien escrito)
    List<Alojamiento> findByProveedor(String proveedor);

    // Buscar alojamientos entre fechas
    List<Alojamiento> findByFechaInicioBetween(Date inicio, Date fin);

    // Buscar alojamientos por cantidad de personas
    List<Alojamiento> findByCantPersonas(int cantPersonas);
}

