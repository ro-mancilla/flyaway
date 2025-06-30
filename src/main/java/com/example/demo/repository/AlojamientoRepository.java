package com.example.demo.repository;

import com.example.demo.model.Alojamiento;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface AlojamientoRepository extends JpaRepository<Alojamiento, Integer> {
    
    Optional<Alojamiento> findByIdAlojamiento(Integer idAlojamiento);
}