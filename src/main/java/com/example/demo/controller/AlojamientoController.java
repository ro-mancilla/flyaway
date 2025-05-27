package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Alojamiento;
import com.example.demo.service.AlojamientoService;
@RestController
@RequestMapping("/api/v1/alojamiento")
public class AlojamientoController { 
     @Autowired
    private AlojamientoService alojamientoService;

    @GetMapping
    public List<Alojamiento> listarTodos() {
        return alojamientoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Alojamiento obtenerPorId(@PathVariable int id) {
        return alojamientoService.obtenerPorId(id);
    }

    @PostMapping
    public Alojamiento crear(@RequestBody Alojamiento alojamiento) {
        return alojamientoService.guardar(alojamiento);
    }

    @PutMapping("/{id}")
    public Alojamiento actualizar(@PathVariable int id, @RequestBody Alojamiento alojamiento) {
        alojamiento.setIdAlojamiento(id);
        return alojamientoService.guardar(alojamiento);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        alojamientoService.eliminar(id);
    }

    @GetMapping("/proveedor/{provedor}")
    public List<Alojamiento> buscarPorProvedor(@PathVariable String provedor) {
        return alojamientoService.buscarPorProvedor(provedor);
    }

    @GetMapping("/fechas")
    public List<Alojamiento> buscarPorFechas(@RequestParam Date inicio, @RequestParam Date fin) {
        return alojamientoService.buscarPorFechas(inicio, fin);
    }

    @GetMapping("/personas/{cantPersonas}")
    public List<Alojamiento> buscarPorCantidadPersonas(@PathVariable int cantPersonas) {
        return alojamientoService.buscarPorCantidadPersonas(cantPersonas);
    }
}
