package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Reserva;
import com.example.demo.service.ReservaService;

@RestController
@RequestMapping("/api/v1/reserva")
public class ReservaController { 
    @Autowired  
    private ReservaService reservaService; 

    @GetMapping
    public List<Reserva> getAllReserva() {
        return reservaService.getAllReserva();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Integer id) {
        return reservaService.getReservaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Reserva createReserva(@RequestBody Reserva reserva) {
        return reservaService.saveReserva(reserva);
    } 
    
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateCliente(@PathVariable Integer id, @RequestBody Reserva reserva) {
        return reservaService.updateReserva(id, reserva)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    } 
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Integer id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}
