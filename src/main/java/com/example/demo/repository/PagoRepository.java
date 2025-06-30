package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.Pago;



public interface PagoRepository extends JpaRepository<Pago, Integer> { 
    Optional<Pago> findById(Integer id);

}
