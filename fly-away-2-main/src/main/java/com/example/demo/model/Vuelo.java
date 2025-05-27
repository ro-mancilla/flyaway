package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    //@Column(unique=true, length = 13, nullable=false) // unique y length no para int
    @Column(nullable = false)
    private Integer idUsuario; // cambie a Integer, sin unique ni length

    @Column(nullable = false)
    private String tipoVuelo;

    @Column(nullable = true)
    private Date fechaInicio;

    @Column(nullable = true)
    private Date fechaFinal;

    @Column(nullable = false)
    private String proveedor;  // corregido

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private boolean disponibilidadServicio;  // corregido

    @Column(nullable = false)
    private String categoria;



}
