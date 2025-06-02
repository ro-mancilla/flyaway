package com.example.demo.service;


import java.util.Optional;


import org.springframework.stereotype.Service;


import com.example.demo.model.Pago;

import com.example.demo.repository.PagoRepository;

@Service
public class pagoService {  
    private final PagoRepository pagoRepository;


    public pagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }


    public Optional<Pago> findById(Integer idPago) {
        return pagoRepository.findById(idPago);
    }

    public Pago savePago(Pago pago) {
        return pagoRepository.save(pago);
    }

    public void deletePago(Integer idPago) {
        pagoRepository.deleteById(idPago);
    }

    public Iterable<Pago> findAll() {
        return pagoRepository.findAll();
    }


    
}
