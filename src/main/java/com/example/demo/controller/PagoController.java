package com.example.demo.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Pago;
import com.example.demo.service.pagoService;

@RestController
@RequestMapping("/api/v1/pago")
public class PagoController {

    private final pagoService pagoServicex;

    public PagoController(pagoService pagoServicex) {
        this.pagoServicex = pagoServicex;
    }

    // Obtener todos los pagos
    @GetMapping
    public ResponseEntity<Iterable<Pago>> getAllPagos() {
        Iterable<Pago> pagos = pagoServicex.findAll();
        return ResponseEntity.ok(pagos);
    }

    // Obtener pago por id
    @GetMapping("/{id}")
    public ResponseEntity<Pago> getPagoById(@PathVariable Integer id) {
        Optional<Pago> pago = pagoServicex.findById(id);
        return pago.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo pago
    @PostMapping
    public ResponseEntity<Pago> createPago(@RequestBody Pago pago) {
        Pago nuevoPago = pagoServicex.savePago(pago);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }

    // Eliminar un pago por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable Integer id) {
        pagoServicex.deletePago(id);
        return ResponseEntity.noContent().build();
    }
}