package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.Factura;


public interface FacturaRepository  extends JpaRepository<Factura, Integer> {  
    Optional<Factura> findById(Integer id);


    


}
