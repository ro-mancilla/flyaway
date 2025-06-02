package com.example.demo.service;

import com.example.demo.model.Alojamiento;
import com.example.demo.repository.AlojamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlojamientoService {

    @Autowired
    private AlojamientoRepository alojamientoRepository;

    public Optional<Alojamiento> findByIdAlojamiento(Integer idAlojamiento) {
        return alojamientoRepository.findByIdAlojamiento(idAlojamiento);
    }

  
    public Alojamiento saveAlojamiento(Alojamiento alojamiento) {
        return alojamientoRepository.save(alojamiento);
    }

    public void deleteAlojamiento(Integer idAlojamiento) {
        alojamientoRepository.deleteById(idAlojamiento);
    }

    public Iterable<Alojamiento> findAll() {
        return alojamientoRepository.findAll();
    }
}
