package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.servicios;
import com.example.demo.repository.ServicioRepository;
@Service
public class ServiciosService { 
    @Autowired
    private ServicioRepository servicioRepository;

    // Obtener todos los servicios
    public List<servicios> obtenerTodos() {
        return servicioRepository.findAll();
    }

    // Obtener un servicio por ID
    public Optional<servicios> obtenerPorId(int id) {
        return servicioRepository.findById(id);
    }

    // Guardar un nuevo servicio
    public servicios guardarServicio(servicios servicio) {
        return servicioRepository.save(servicio);
    }

    // Eliminar un servicio por ID
    public void eliminarServicio(int id) {
        servicioRepository.deleteById(id);
    }

    // Obtener servicios por ID de usuario
    public List<servicios> obtenerPorUsuario(int idUsuario) {
        return servicioRepository.findByIdUsuario(idUsuario);
    }

    // Obtener servicios por tipo
    public List<servicios> obtenerPorTipo(String tipo) {
        return servicioRepository.findByTipoServicio(tipo);
    }

    // Obtener servicios por categoría
    public List<servicios> obtenerPorCategoria(String categoria) {
        return servicioRepository.findByCategoria(categoria);
    }

    // Obtener servicios disponibles
    public List<servicios> obtenerDisponibles() {
        return servicioRepository.findByDisponiblidadServicioTrue();
    }

    // Obtener servicios por proveedor
    public List<servicios> obtenerPorProveedor(String proveedor) {
        return servicioRepository.findByProveedor(proveedor);
    }

}
