package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;

import com.example.demo.repository.ClienteRepository;
@Service
public class clienteService { 
    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener todos los clientes
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    // Obtener cliente por ID
    public Optional<Cliente> getClienteById(Integer id) {
        return clienteRepository.findById(id);
    }

    // Guardar nuevo cliente
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    } 

    // Actualizar cliente existente
    public Optional<Cliente> updateCliente(Integer id, Cliente clienteActualizado) {
        return clienteRepository.findById(id).map(clienteExistente -> {
            clienteExistente.setRut(clienteActualizado.getRut());
            clienteExistente.setNombres(clienteActualizado.getNombres());
            clienteExistente.setApellidos(clienteActualizado.getApellidos());
            clienteExistente.setFechaNacimiento(clienteActualizado.getFechaNacimiento());
            clienteExistente.setCorreo(clienteActualizado.getCorreo());
            clienteExistente.setTelefono(clienteActualizado.getTelefono());
            return clienteRepository.save(clienteExistente);
        });
    }

    // Eliminar cliente por ID
    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
   
}