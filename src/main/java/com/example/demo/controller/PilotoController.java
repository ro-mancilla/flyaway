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


import com.example.demo.model.Piloto;
import com.example.demo.service.PilotoService;

@RestController
@RequestMapping("/api/v1/piloto")
public class PilotoController { 
    @Autowired 
    private PilotoService pilotoService; 
    
    @GetMapping  
    public List<Piloto> getAllPilotos() { 
        return pilotoService.getAllPilotos(); 
    }
    
    
    
    
    
    
    @GetMapping("/{id}") 
    public ResponseEntity <Piloto> getPilotoById (@PathVariable Integer id) { 
        return pilotoService.getPilotoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); 
    } 
    
    @PostMapping 
    public Piloto createPiloto(@RequestBody Piloto piloto) {
        return pilotoService.savePiloto(piloto);
    }  
    @PutMapping("/{id}") 
    public ResponseEntity <Piloto> updatePiloto(@PathVariable Integer id, @RequestBody Piloto piloto) {
        return pilotoService.updateCliente(id, piloto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePiloto(@PathVariable Integer id) {
        pilotoService.deletePiloto(id);
        return ResponseEntity.noContent().build();
    }

    


}
