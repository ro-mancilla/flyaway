package com.example.demo.repository;

import com.example.demo.model.Alojamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlojamientoRepository extends JpaRepository<Alojamiento, Integer> {
    
    Optional<Alojamiento> findByIdAlojamiento(Integer idAlojamiento);
}