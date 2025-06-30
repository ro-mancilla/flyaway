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


import com.example.demo.model.Transaccion;
import com.example.demo.service.TransaccionService;


@RestController
@RequestMapping("/api/v1/transaccion")
public class TransaccionController {  
    @Autowired 
    private TransaccionService transaccionService; 

    @GetMapping
    public List<Transaccion> getAllTransaccion() {
        return transaccionService.getAllTransaccion();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> getPagoById(@PathVariable Integer id) {
        return transaccionService.getTransaccionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    } 
    
    @PostMapping
    public Transaccion createTransaccion(@RequestBody Transaccion transaccion) {
        return transaccionService.saveTransaccion(transaccion);
    } 

    @PutMapping("/{id}")
    public ResponseEntity<Transaccion> updateTransaccion(@PathVariable Integer id, @RequestBody Transaccion transaccion) {
        return transaccionService.updateTransaccion(id, transaccion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    } 

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaccion(@PathVariable Integer id) {
        transaccionService.deleteTransaccion(id);
        return ResponseEntity.noContent().build();
    }

}
