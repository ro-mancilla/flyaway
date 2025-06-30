package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.Habitacion;



public interface HabitacionRepository extends JpaRepository <Habitacion, Integer>{ 
    Optional<Habitacion> findById(Integer id);

    

}
