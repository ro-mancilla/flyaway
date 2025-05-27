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
public class Cliente { 
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Integer id;
    
    @Column(unique=true, length = 13, nullable=false)
    private String rut;
    
    @Column(nullable=false)
    private String nombres;
    
    @Column(nullable=false)
    private String apellidos;
    
    @Column(nullable=true)
    private Date fechaNacimiento;
    
    @Column(nullable=false)
    private String correo;

    @Column(nullable=false)
    private int telefono;

}
