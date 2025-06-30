package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Piloto;
import com.example.demo.repository.PilotoRepository;

@Service
public class PilotoService { 
    @Autowired 
    private PilotoRepository pilotoRepository;

    public List <Piloto> getAllPilotos(){ 
        return pilotoRepository.findAll();
        
    }
    public Optional<Piloto> getPilotoById(Integer id) {
        return pilotoRepository.findById(id);
    } 
     
    public Piloto savePiloto(Piloto piloto) {
        return pilotoRepository.save(piloto);
    }

    public Optional<Piloto> updateCliente(Integer id, Piloto pilotoActualizado) {
        return pilotoRepository.findById(id).map(pilotoExistente -> {
            pilotoExistente.setNombre(pilotoActualizado.getNombre());
            pilotoExistente.setRut(pilotoActualizado.getRut()); 
            pilotoExistente.setLicencia(pilotoActualizado.getLicencia()); 
            pilotoExistente.setHorasVuelo(pilotoActualizado.getHorasVuelo());
            return pilotoRepository.save(pilotoExistente);
        });
    }  
    public void deletePiloto(Integer id) {
        pilotoRepository.deleteById(id);
    }
    


}
