package com.example.demo.service;

import java.util.Optional;


import java.util.List;


import org.springframework.stereotype.Service;

import com.example.demo.model.Pago;


import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.PagoRepository;
@Service
public class PagoServicex {   
    @Autowired 
    private PagoRepository pagoRepository; 

    public List<Pago> getAllPago() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> getPagoById(Integer id) {
        return pagoRepository.findById(id);
    }  

    public Pago savePago(Pago pago) {
        return pagoRepository.save(pago);
    }  
    
    public Optional<Pago> updatePago(Integer id, Pago pagoActualizado) {
        return pagoRepository.findById(id).map(pagoExistente -> {
            pagoExistente.setEstado(pagoActualizado.getEstado());
            pagoExistente.setMonto(pagoActualizado.getMonto());
            pagoExistente.setMoneda(pagoActualizado.getMoneda()); 
            pagoExistente.setDescripcion(pagoActualizado.getDescripcion());
            return pagoRepository.save(pagoExistente);
        });
    } 

    public void deletePago(Integer id) {
        pagoRepository.deleteById(id);
    }


}
