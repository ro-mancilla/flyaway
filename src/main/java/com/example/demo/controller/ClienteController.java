package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cliente;
import com.example.demo.service.clienteService;






@RestController 
@RequestMapping("/api/v1/cliente")


public class ClienteController {  
    @Autowired 
    private clienteService clienteservicex6;  
    @GetMapping 
    public List <Cliente> listarClientes(){
        return clienteservicex6.getClientes();
    } 
    @PostMapping
    public Cliente agregarCliente(@RequestBody Cliente cliente){
        return clienteservicex6.saveClientes(cliente);
    } 
    @GetMapping("{id}") 
    public Cliente buscarCliente (@PathVariable int id){
        return clienteservicex6.getClientesId(id); 
    }
    @DeleteMapping("{id}")  
    public String eliminarCliente (@PathVariable int id){
        return clienteservicex6.deleteClientesId(id);
    }
    


}
