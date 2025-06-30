package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Piloto;

public interface PilotoRepository extends JpaRepository <Piloto, Integer>{ 
    Optional<Piloto> findById(Integer id);
}
