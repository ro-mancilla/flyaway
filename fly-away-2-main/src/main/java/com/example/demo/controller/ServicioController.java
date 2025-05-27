package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.servicios;
import com.example.demo.service.ServiciosService;
@RestController
@RequestMapping("/api/v1/servicio")
public class ServicioController { 
    @Autowired
    private ServiciosService servicioService;

    // GET: Obtener todos los servicios
    @GetMapping
    public List<servicios> obtenerTodos() {
        return servicioService.obtenerTodos();
    }

    // GET: Obtener servicio por ID
    @GetMapping("/{id}")
    public Optional<servicios> obtenerPorId(@PathVariable int id) {
        return servicioService.obtenerPorId(id);
    }

    // POST: Crear nuevo servicio
    @PostMapping
    public servicios crearServicio(@RequestBody servicios servicio) {
        return servicioService.guardarServicio(servicio);
    }

    // DELETE: Eliminar servicio por ID
    @DeleteMapping("/{id}")
    public void eliminarServicio(@PathVariable int id) {
        servicioService.eliminarServicio(id);
    }

    // GET: Obtener servicios por ID de usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<servicios> obtenerPorUsuario(@PathVariable int idUsuario) {
        return servicioService.obtenerPorUsuario(idUsuario);
    }

    // GET: Obtener servicios por tipo
    @GetMapping("/tipo/{tipo}")
    public List<servicios> obtenerPorTipo(@PathVariable String tipo) {
        return servicioService.obtenerPorTipo(tipo);
    }

    // GET: Obtener servicios por categoría
    @GetMapping("/categoria/{categoria}")
    public List<servicios> obtenerPorCategoria(@PathVariable String categoria) {
        return servicioService.obtenerPorCategoria(categoria);
    }

    // GET: Obtener servicios disponibles
    @GetMapping("/disponibles")
    public List<servicios> obtenerDisponibles() {
        return servicioService.obtenerDisponibles();
    }

    // GET: Obtener servicios por proveedor
    @GetMapping("/proveedor/{proveedor}")
    public List<servicios> obtenerPorProveedor(@PathVariable String proveedor) {
        return servicioService.obtenerPorProveedor(proveedor);
    }


}
