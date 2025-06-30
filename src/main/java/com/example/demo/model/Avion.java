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
public class Avion { 
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Integer idAvion; 

    @Column(nullable=false)
    private String modelo; 

    @Column(nullable=false)
    private String fabricante; 
    
    @Column(nullable=false) 
    private String matricula; 

    @Column (nullable=false) 
    private int capacidad;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
