package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pago;
import com.example.demo.service.pagoService;
@RestController
@RequestMapping("/api/v1/pago")
public class PagoController { 
    @Autowired
    private pagoService PagoService;

    // Obtener todos los pagos
    @GetMapping
    public List<Pago> obtenerPagos() {
        return PagoService.obtenerTodos();
    }

    // Obtener pago por ID
    @GetMapping("/{id}")
    public Pago obtenerPorId(@PathVariable Integer id) {
        return PagoService.buscarPorId(id);
    }

    // Crear nuevo pago
    @PostMapping
    public Pago crearPago(@RequestBody Pago pago) {
        return PagoService.guardar(pago);
    }

    // Actualizar pago
    @PutMapping("/{id}")
    public Pago actualizarPago(@PathVariable Integer id, @RequestBody Pago pago) {
        pago.setIdPago(id);
        return PagoService.guardar(pago);
    }

    // Eliminar pago
    @DeleteMapping("/{id}")
    public void eliminarPago(@PathVariable Integer id) {
        PagoService.eliminar(id);
    }

    // Buscar pagos por ID de usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<Pago> pagosPorUsuario(@PathVariable int idUsuario) {
        return PagoService.buscarPorIdUsuario(idUsuario);
    }

    // Buscar pagos por estado
    @GetMapping("/estado/{estado}")
    public List<Pago> pagosPorEstado(@PathVariable String estado) {
        return PagoService.buscarPorEstado(estado);
    }

    // Buscar pagos entre dos fechas
    @GetMapping("/fecha")
    public List<Pago> pagosPorFechas(@RequestParam Date inicio, @RequestParam Date fin) {
        return PagoService.buscarPorFechas(inicio, fin);
    }

}
