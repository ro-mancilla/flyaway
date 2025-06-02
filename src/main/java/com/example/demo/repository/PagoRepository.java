package com.example.demo.repository;


import java.util.Optional;


import com.example.demo.model.Pago;


import org.springframework.data.jpa.repository.JpaRepository;


public interface PagoRepository extends JpaRepository<Pago, Integer> {
    
    Optional<Pago> findByIdPago(Integer idPago);
}