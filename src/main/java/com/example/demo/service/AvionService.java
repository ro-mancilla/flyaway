package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Avion;
import com.example.demo.repository.AvionRepository;
@Service
public class AvionService { 
    @Autowired 
    private AvionRepository avionRepository; 
    
    public List <Avion> getAllAvion(){  
        return avionRepository.findAll();


    }
    public Optional <Avion> getAvionId(Integer idAvion){  
        return avionRepository.findById(idAvion);

        
    } 
    public Avion saveAvion(Avion avion){
        return avionRepository.save(avion);
    } 

    public Optional <Avion> updateAvion (Integer idAvion, Avion avionActualizado){ 
        return avionRepository.findById(idAvion).map(avionExistente -> {  
            avionExistente.setModelo(avionActualizado.getModelo()); 
            avionExistente.setFabricante(avionActualizado.getFabricante()); 
            avionExistente.setMatricula(avionActualizado.getMatricula()); 
            avionExistente.setCapacidad(avionActualizado.getCapacidad()); 
            return avionRepository.save(avionExistente);
        });       


    };
    public void deleteAvion(Integer idAvion) {
        avionRepository.deleteById(idAvion);
    }

}
