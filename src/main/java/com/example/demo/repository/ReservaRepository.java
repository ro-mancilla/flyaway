package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.Reserva;


public interface ReservaRepository extends JpaRepository <Reserva, Integer> { 
    Optional <Reserva> findById(Integer id); 




}
