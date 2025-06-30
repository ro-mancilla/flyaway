package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Avion;

public interface AvionRepository extends JpaRepository <Avion, Integer>{ 
    Optional<Avion> findById(Integer id);
}