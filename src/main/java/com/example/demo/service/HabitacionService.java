package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Habitacion;

import com.example.demo.repository.HabitacionRepository;

@Service
public class HabitacionService { 
    @Autowired 
    private HabitacionRepository habitacionRepository; 

    public List <Habitacion> getAllHabitaciones(){ 
        return habitacionRepository.findAll(); 
        
    }
    public Optional<Habitacion> getHabitacionById(Integer id) {
        return habitacionRepository.findById(id);
    } 

    public Habitacion saveHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    } 

    public Optional<Habitacion> updateHabitacion(Integer id, Habitacion habitacionActualizado) {
        return habitacionRepository.findById(id).map(habitacionExistente -> {
            habitacionExistente.setNumeroHabitacion(habitacionActualizado.getNumeroHabitacion());
            habitacionExistente.setTipoHabitacion(habitacionActualizado.getTipoHabitacion());
            habitacionExistente.setPrecioPorNoche(habitacionActualizado.getPrecioPorNoche());
            return habitacionRepository.save(habitacionExistente);
        });
    }

    public void deleteHabitacion(Integer id) {
            habitacionRepository.deleteById(id);
    }


}
