package com.example.demo.model;

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
public class Piloto { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable=false)
    private String nombre;
    
    @Column(nullable=false)
    private String rut;
    
    @Column(nullable=false)
    private String licencia;
    
    @Column(nullable=false)
    private int horasVuelo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
