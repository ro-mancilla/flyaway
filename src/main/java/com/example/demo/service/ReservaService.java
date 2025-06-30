package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Reserva;
import com.example.demo.repository.ReservaRepository;

@Service
public class ReservaService { 
    @Autowired 
    private ReservaRepository reservaRepository; 

    public List <Reserva> getAllReserva(){ 
        return reservaRepository.findAll();
        
    }
    
    public Optional<Reserva> getReservaById(Integer id) {
        return reservaRepository.findById(id);
    }  

    public Reserva saveReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    } 
    
    public Optional<Reserva> updateReserva(Integer id, Reserva reservaActualizado) {
        return reservaRepository.findById(id).map(reservaExistente -> {
            
            reservaExistente.setEstado(reservaActualizado.getEstado());
            reservaExistente.setMontoTotal(reservaActualizado.getMontoTotal());
            return reservaRepository.save(reservaExistente);
        });
    } 

    public void deleteReserva(Integer id) {
            reservaRepository.deleteById(id);
    }

}
