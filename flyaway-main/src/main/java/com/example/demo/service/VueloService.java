package com.example.demo.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Vuelo;
import com.example.demo.repository.VueloRepository;


@Service
public class VueloService { 
    @Autowired
    private VueloRepository vueloRepository;

    // Obtener todos los vuelos
    public List<Vuelo> obtenerTodosLosVuelos() {
        return vueloRepository.findAll();
    }

    // Buscar vuelo por ID
    public Vuelo obtenerVueloPorId(int id) {
        Optional<Vuelo> optionalVuelo = vueloRepository.findById(id);
        return optionalVuelo.orElse(null);
    }

    // Guardar nuevo vuelo o actualizar
    public Vuelo guardarVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    // Eliminar vuelo por ID
    public void eliminarVuelo(int id) {
        vueloRepository.deleteById(id);
    }

    // Buscar por ID de usuario
    public List<Vuelo> obtenerVuelosPorUsuario(int idUsuario) {
        return vueloRepository.findByIdUsuario(idUsuario);
    }

    // Buscar por categoría
    public List<Vuelo> obtenerVuelosPorCategoria(String categoria) {
        return vueloRepository.findByCategoria(categoria);
    }

    // Buscar vuelos disponibles
    public List<Vuelo> obtenerVuelosDisponibles() {
        return vueloRepository.findByDisponibilidadServicioTrue();
    }

    // Buscar vuelos entre fechas
    public List<Vuelo> obtenerVuelosPorFechas(Date inicio, Date fin) {
        return vueloRepository.findByFechaInicioBetween(inicio, fin);
    }




}
