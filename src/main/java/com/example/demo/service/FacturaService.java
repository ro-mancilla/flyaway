package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Factura;

import com.example.demo.repository.FacturaRepository;

@Service
public class FacturaService { 
    @Autowired 
    private FacturaRepository facturaRepository; 

    public List<Factura> getAllFactura() {
        return facturaRepository.findAll();
    }
    
    public Optional<Factura> getFacturaById(Integer id) {
        return facturaRepository.findById(id);
    } 

    public Factura saveFactura(Factura factura) {
        return facturaRepository.save(factura);
    } 

    public Optional<Factura> updateFactura(Integer id, Factura facturaActualizado) {
        return facturaRepository.findById(id).map(facturaExistente -> {
            facturaExistente.setNumeroFactura(facturaActualizado.getNumeroFactura());
            facturaExistente.setTotal(facturaActualizado.getTotal());
            facturaExistente.setRazonSocial(facturaActualizado.getRazonSocial());
            return facturaRepository.save(facturaExistente);
        });
    } 

    public void deleteFactura(Integer id) {
        facturaRepository.deleteById(id);
    }
}
