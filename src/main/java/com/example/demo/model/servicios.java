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
public class servicios { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServicio;

    @Column(nullable = false)
    private int idUsuario;

    @Column(nullable = false)
    private String tipoServicio;

    @Column(nullable = true)
    private Date fechaInicio;

    @Column(nullable = true)
    private Date fechaFinal;

    @Column(nullable = false)
    private String proveedor;

    @Column(nullable = false)
    private int monto;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private boolean disponiblidadServicio;

    @Column(nullable = false)
    private String categoria;

}
