package com.example.demo.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
@Entity
public class Pago { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    @Column(nullable = false)
    private Integer idUsuario;
    
    @Column(nullable = true)
    private LocalDate fechaPago;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private int monto;

    @Column(nullable = false)
    private String moneda;

    @Column(nullable = false)
    private String descripcion;
}
