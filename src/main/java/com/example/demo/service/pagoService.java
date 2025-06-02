package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pago;
import com.example.demo.repository.PagoRepository;





@Service
public class pagoService {  
    @Autowired
    private PagoRepository pagoRepository;

    // Obtener todos los pagos
    public List<Pago> obtenerTodos() {
        return pagoRepository.findAll();
    }

    // Buscar pago por ID
    public Pago buscarPorId(Integer id) {
        return pagoRepository.findById(id).orElse(null);
    }

    // Guardar un nuevo pago o actualizar
    public Pago guardar(Pago pago) {
        return pagoRepository.save(pago);
    }

    // Eliminar un pago
    public void eliminar(Integer id) {
        pagoRepository.deleteById(id);
    }

    // Buscar pagos por ID de usuario
    public List<Pago> buscarPorIdUsuario(int idUsuario) {
        return pagoRepository.findByIdUsuario(idUsuario);
    }

    // Buscar pagos por estado
    public List<Pago> buscarPorEstado(String estado) {
        return pagoRepository.findByEstado(estado);
    }

    // Buscar pagos entre fechas
    public List<Pago> buscarPorFechas(Date inicio, Date fin) {
        return pagoRepository.findByFechaPagoBetween(inicio, fin);
    }
    


    
}

