package com.flyaway.project.flyaway.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name= "Cliente")
@AllArgsConstructor
@NoArgsConstructor
@Data

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
