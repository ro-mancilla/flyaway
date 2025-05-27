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

import com.example.demo.model.Vuelo;
import com.example.demo.service.VueloService;
@RestController
@RequestMapping("/api/v1/vuelo")
public class VueloController { 
    @Autowired
    private VueloService vueloService;

    // GET: Obtener todos los vuelos
    @GetMapping
    public List<Vuelo> obtenerTodos() {
        return vueloService.obtenerTodosLosVuelos();
    }

    // GET: Obtener vuelo por ID
    @GetMapping("/{id}")
    public Vuelo obtenerPorId(@PathVariable int id) {
        return vueloService.obtenerVueloPorId(id);
    }

    // POST: Crear nuevo vuelo
    @PostMapping
    public Vuelo crearVuelo(@RequestBody Vuelo vuelo) {
        return vueloService.guardarVuelo(vuelo);
    }

    // PUT: Actualizar vuelo existente
    @PutMapping("/{id}")
    public Vuelo actualizarVuelo(@PathVariable int id, @RequestBody Vuelo vuelo) {
        vuelo.setIdVuelo(null);
        return vueloService.guardarVuelo(vuelo);
    }

    // DELETE: Eliminar vuelo por ID
    @DeleteMapping("/{id}")
    public void eliminarVuelo(@PathVariable int id) {
        vueloService.eliminarVuelo(id);
    }

    // GET: Buscar vuelos por ID de usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<Vuelo> obtenerPorUsuario(@PathVariable int idUsuario) {
        return vueloService.obtenerVuelosPorUsuario(idUsuario);
    }

    // GET: Buscar vuelos por categoría
    @GetMapping("/categoria/{categoria}")
    public List<Vuelo> obtenerPorCategoria(@PathVariable String categoria) {
        return vueloService.obtenerVuelosPorCategoria(categoria);
    }

    // GET: Buscar vuelos disponibles
    @GetMapping("/disponibles")
    public List<Vuelo> obtenerDisponibles() {
        return vueloService.obtenerVuelosDisponibles();
    }

    // GET: Buscar vuelos por rango de fechas
    @GetMapping("/fechas")
    public List<Vuelo> obtenerPorFechas(
        @RequestParam Date inicio,
        @RequestParam Date fin
    ) {
        return vueloService.obtenerVuelosPorFechas(inicio, fin);
    }

}
