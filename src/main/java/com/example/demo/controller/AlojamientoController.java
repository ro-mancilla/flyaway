package com.example.demo.controller;

import com.example.demo.model.Alojamiento;
import com.example.demo.service.AlojamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/alojamientos")
public class AlojamientoController {

    @Autowired
    private AlojamientoService alojamientoService;

    @GetMapping("/{id}")
    public ResponseEntity<Alojamiento> getAlojamientoById(@PathVariable Integer id) {
        Optional<Alojamiento> alojamiento = alojamientoService.findByIdAlojamiento(id);
        return alojamiento.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Alojamiento> createAlojamiento(@RequestBody Alojamiento alojamiento) {
        Alojamiento saved = alojamientoService.saveAlojamiento(alojamiento);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlojamiento(@PathVariable Integer id) {
        alojamientoService.deleteAlojamiento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Alojamiento>> getAllAlojamientos() {
        return ResponseEntity.ok(alojamientoService.findAll());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Alojamiento> updateAlojamiento(@PathVariable Integer id, @RequestBody Alojamiento alojamientoActualizado) {
    Optional<Alojamiento> alojamientoExistente = alojamientoService.findByIdAlojamiento(id);

    if (alojamientoExistente.isPresent()) {
        Alojamiento alojamiento = alojamientoExistente.get();
        alojamiento.setTipoAlojamiento(alojamientoActualizado.getTipoAlojamiento());
        alojamiento.setProveedor(alojamientoActualizado.getProveedor());
        alojamiento.setDireccion(alojamientoActualizado.getDireccion());
        alojamiento.setCantPersonas(alojamientoActualizado.getCantPersonas());
        alojamiento.setCliente(alojamientoActualizado.getCliente());

        Alojamiento actualizado = alojamientoService.saveAlojamiento(alojamiento);
        return ResponseEntity.ok(actualizado);
    } else {
        return ResponseEntity.notFound().build();
    }
}



}