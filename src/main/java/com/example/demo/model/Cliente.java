package com.example.demo.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Habitacion> habitaciones; 

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Alojamiento> alojamientos;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Reserva> reservas;
}
