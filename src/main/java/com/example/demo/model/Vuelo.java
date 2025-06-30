package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data 
@AllArgsConstructor 
@NoArgsConstructor
@Entity

public class Vuelo { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVuelo;  // corregido nombre


    @Column(nullable = false)
    private Integer idUsuario; 

    @Column(nullable = false)
    private String tipoVuelo;

    @Column(nullable = true)
    private Date fechaInicio;

    @Column(nullable = true)
    private Date fechaFinal;

    @Column(nullable = false)
    private String proveedor;  

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private boolean disponibilidadServicio;  

    @Column(nullable = false)
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
}
