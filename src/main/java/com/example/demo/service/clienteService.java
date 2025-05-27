package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
@Service
public class clienteService { 
    @Autowired 
    private ClienteRepository clienteRepository; 

    public List <Cliente> getClientes(){
        return clienteRepository.obtenerClientes();
    }
    public Cliente saveClientes(Cliente cli){
        return clienteRepository.guardar(cli);
    } 

    public Cliente getClientesId(int id){
        return clienteRepository.buscarPorId(id);
    } 

    public String deleteClientesId(int id){
        clienteRepository.eliminar(id); 
        return "cliente eliminado"; 
    } 
}
