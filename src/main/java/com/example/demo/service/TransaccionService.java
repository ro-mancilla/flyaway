package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Transaccion;

import com.example.demo.repository.TransaccionRepository;

@Service
public class TransaccionService {  
    @Autowired
    private TransaccionRepository transaccionRepository; 

    public List<Transaccion> getAllTransaccion() {
        return transaccionRepository.findAll(); 


    }
    
    public Optional<Transaccion> getTransaccionById(Integer id) {
        return transaccionRepository.findById(id);
    }

    public Transaccion saveTransaccion(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    } 

    public Optional<Transaccion> updateTransaccion(Integer id, Transaccion transaccionActualizado) {
        return transaccionRepository.findById(id).map(transaccionExistente -> {
            transaccionExistente.setEstado(transaccionActualizado.getEstado());
            transaccionExistente.setPasarela(transaccionActualizado.getPasarela());
            return transaccionRepository.save(transaccionExistente);
        });
    } 

    public void deleteTransaccion(Integer id) {
        transaccionRepository.deleteById(id);
    }
}
