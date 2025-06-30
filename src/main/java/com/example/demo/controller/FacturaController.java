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


import com.example.demo.model.Factura;
import com.example.demo.service.FacturaService;


@RestController
@RequestMapping("/api/v1/factura")
public class FacturaController { 
    @Autowired
    private FacturaService facturaService; 

    @GetMapping
    public List<Factura> getAllFactura() {
        return facturaService.getAllFactura();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Integer id) {
        return facturaService.getFacturaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    } 

    @PostMapping
    public Factura createFactura(@RequestBody Factura factura) {
        return facturaService.saveFactura(factura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> updateFactura(@PathVariable Integer id, @RequestBody Factura factura) {
        return facturaService.updateFactura(id, factura)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    } 

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Integer id) {
        facturaService.deleteFactura(id);
        return ResponseEntity.noContent().build();
    }


}
