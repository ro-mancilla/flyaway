package com.example.demo.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Alojamiento;
import com.example.demo.repository.AlojamientoRepository;
@Service
public class AlojamientoService { 
     @Autowired
    private AlojamientoRepository alojamientoRepository;

    public List<Alojamiento> obtenerTodos() {
        return alojamientoRepository.findAll();
    }

    public Alojamiento obtenerPorId(int id) {
        Optional<Alojamiento> optional = alojamientoRepository.findById(id);
        return optional.orElse(null);
    }

    public Alojamiento guardar(Alojamiento alojamiento) {
        return alojamientoRepository.save(alojamiento);
    }

    public void eliminar(int id) {
        alojamientoRepository.deleteById(id);
    }

    public List<Alojamiento> buscarPorProvedor(String provedor) {
        return alojamientoRepository.findByProveedor(provedor);
    }

    public List<Alojamiento> buscarPorFechas(Date inicio, Date fin) {
        return alojamientoRepository.findByFechaInicioBetween(inicio, fin);
    }

    public List<Alojamiento> buscarPorCantidadPersonas(int cantPersonas) {
        return alojamientoRepository.findByCantPersonas(cantPersonas);
    }

}
