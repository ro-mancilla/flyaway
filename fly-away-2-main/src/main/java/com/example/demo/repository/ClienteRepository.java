package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Cliente;

@Repository

public class ClienteRepository {  
    private List <Cliente> listaCliente = new ArrayList<>(); 

    public List <Cliente> obtenerClientes (){ 
        return listaCliente;

    }
    public Cliente buscarPorId (int id){
        for (Cliente cliente : listaCliente){
            if (cliente.getId() == id){
                return cliente;
            }
        }
                return null;
    }
    public Cliente buscarPorNombre (String name){
        for (Cliente cliente : listaCliente){
            if (cliente.getNombres() == name){
                return cliente;
            }
        }
                return null;
    } 

    public Cliente guardar (Cliente cli){
        listaCliente.add(cli); 
        return cli; 
    }
    public void eliminar (int id){
        Cliente cliente = buscarPorId(id); 
        if (cliente != null){
            listaCliente.remove(cliente);
        }
    }







}
