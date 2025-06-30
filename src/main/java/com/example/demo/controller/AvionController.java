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

import com.example.demo.model.Avion;
import com.example.demo.service.AvionService;


@RestController
@RequestMapping("/api/v1/avion")
public class AvionController { 
    @Autowired 
    private AvionService avionService; 

    @GetMapping 
    public List <Avion> getAllAvion(){ 
        return avionService.getAllAvion();
        
    } 
    @GetMapping("/{id}") 
    public ResponseEntity <Avion> getAvionId(@PathVariable Integer id) {
        return avionService.getAvionId(id) 
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());


    } 
    @PostMapping 
    public Avion createAvion(@RequestBody Avion avion){
        return avionService.saveAvion(avion);
    }
    @PutMapping("/{id}") 
    public ResponseEntity <Avion> updateAvion (@PathVariable Integer idAvion, @RequestBody Avion avion){
        return avionService.updateAvion(idAvion, avion) 
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    } 
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> deleteAvion(@PathVariable Integer id) {
        avionService.deleteAvion(id);
        return ResponseEntity.noContent().build();
    }

}
