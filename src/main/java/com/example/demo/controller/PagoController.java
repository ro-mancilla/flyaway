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
import com.example.demo.model.Pago;
import com.example.demo.service.PagoServicex;


@RestController
@RequestMapping("/api/v1/pago")
public class PagoController {  
    @Autowired 
    private PagoServicex pagoService;  

    @GetMapping
    public List<Pago> getAllPago() {
        return pagoService.getAllPago(); 
    } 
    
    @GetMapping("/{id}")
    public ResponseEntity<Pago> getPagoById(@PathVariable Integer id) {
        return pagoService.getPagoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    } 
    
    @PostMapping
    public Pago createPago(@RequestBody Pago pago) {
        return pagoService.savePago(pago);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pago> updateFactura(@PathVariable Integer id, @RequestBody Pago pago) {
        return pagoService.updatePago(id, pago)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    } 
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable Integer id) {
        pagoService.deletePago(id);
        return ResponseEntity.noContent().build();
    }
}
