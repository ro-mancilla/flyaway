package com.example.demo.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;

@DisplayName("Vuelo Entity Unit Tests")
class VueloTest {
    
    private Vuelo vuelo;
    
    @BeforeEach
    void setUp() {
        vuelo = new Vuelo();
    }
    
    @Test
    @DisplayName("Test constructor sin parámetros")
    void testConstructorSinParametros() {
        Vuelo vueloNuevo = new Vuelo();
        assertNotNull(vueloNuevo);
        assertNull(vueloNuevo.getIdVuelo());
    }
    
    @Test
    @DisplayName("Test constructor con todos los parámetros")
    void testConstructorConParametros() {
        Date fechaInicio = Date.valueOf("2024-01-01");
        Date fechaFinal = Date.valueOf("2024-01-02");
        
        Vuelo vueloCompleto = new Vuelo(1, 100, "Internacional", fechaInicio, 
                                       fechaFinal, "Avianca", "Vuelo directo", 
                                       true, "Turismo", null);
        
        assertEquals(1, vueloCompleto.getIdVuelo());
        assertEquals(100, vueloCompleto.getIdUsuario());
        assertEquals("Internacional", vueloCompleto.getTipoVuelo());
        assertEquals(fechaInicio, vueloCompleto.getFechaInicio());
        assertEquals(fechaFinal, vueloCompleto.getFechaFinal());
        assertEquals("Avianca", vueloCompleto.getProveedor());
        assertEquals("Vuelo directo", vueloCompleto.getDescripcion());
        assertTrue(vueloCompleto.isDisponibilidadServicio());
        assertEquals("Turismo", vueloCompleto.getCategoria());
    }
    
    @Test
    @DisplayName("Test setters y getters")
    void testSettersYGetters() {
        vuelo.setIdVuelo(1);
        vuelo.setIdUsuario(100);
        vuelo.setTipoVuelo("Nacional");
        vuelo.setProveedor("LATAM");
        vuelo.setDescripcion("Vuelo con escalas");
        vuelo.setDisponibilidadServicio(false);
        vuelo.setCategoria("Negocios");
        
        assertEquals(1, vuelo.getIdVuelo());
        assertEquals(100, vuelo.getIdUsuario());
        assertEquals("Nacional", vuelo.getTipoVuelo());
        assertEquals("LATAM", vuelo.getProveedor());
        assertEquals("Vuelo con escalas", vuelo.getDescripcion());
        assertFalse(vuelo.isDisponibilidadServicio());
        assertEquals("Negocios", vuelo.getCategoria());
    }
    
    @Test
    @DisplayName("Test validación de fechas")
    void testValidacionFechas() {
        Date fechaInicio = Date.valueOf("2024-01-01");
        Date fechaFinal = Date.valueOf("2024-01-02");
        
        vuelo.setFechaInicio(fechaInicio);
        vuelo.setFechaFinal(fechaFinal);
        
        assertEquals(fechaInicio, vuelo.getFechaInicio());
        assertEquals(fechaFinal, vuelo.getFechaFinal());
        assertTrue(vuelo.getFechaFinal().after(vuelo.getFechaInicio()));
    }
    
    @Test
    @DisplayName("Test equals y hashCode")
    void testEqualsYHashCode() {
        Vuelo vuelo1 = new Vuelo();
        vuelo1.setIdVuelo(1);
        vuelo1.setIdUsuario(100);
        
        Vuelo vuelo2 = new Vuelo();
        vuelo2.setIdVuelo(1);
        vuelo2.setIdUsuario(100);
        
        assertEquals(vuelo1, vuelo2);
        assertEquals(vuelo1.hashCode(), vuelo2.hashCode());
    }
    
    @Test
    @DisplayName("Test toString")
    void testToString() {
        vuelo.setIdVuelo(1);
        vuelo.setProveedor("Avianca");
        
        String toString = vuelo.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("Avianca"));
    }
}