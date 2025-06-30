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
public class Factura { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    
    @Column(nullable=false)
    private String numeroFactura;
    
    @Column(nullable=false)
    private int total;
    
    @Column(nullable=false)
    private String razonSocial; 

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    
}
