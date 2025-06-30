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


import com.example.demo.model.Habitacion;
import com.example.demo.service.HabitacionService;

@RestController
@RequestMapping("/api/v1/habitacion")
public class HabitacionController { 
    @Autowired 
    private HabitacionService habitacionService; 

    @GetMapping
    public List<Habitacion> getAllHabitaciones() {
        return habitacionService.getAllHabitaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> getHabitacionById(@PathVariable Integer id) {
        return habitacionService.getHabitacionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Habitacion createHabitacion(@RequestBody Habitacion habitacion) {
        return habitacionService.saveHabitacion(habitacion);
    } 

    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> updateCliente(@PathVariable Integer id, @RequestBody Habitacion habitacion) {
        return habitacionService.updateHabitacion(id, habitacion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    } 
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitacion(@PathVariable Integer id) {
        habitacionService.deleteHabitacion(id);
        return ResponseEntity.noContent().build();
    }
    
    

}
