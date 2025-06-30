package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> { 
    Optional<Transaccion> findById(Integer id);

}
