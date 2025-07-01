package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.model.Vuelo;
import com.example.demo.repository.VueloRepository;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("VueloService Unit Tests")
class VueloServiceTest {
    
    @Mock
    private VueloRepository vueloRepository;
    
    @InjectMocks
    private VueloService vueloService;
    
    private Vuelo vuelo;
    
    @BeforeEach
    void setUp() {
        vuelo = new Vuelo();
        vuelo.setIdVuelo(1);
        vuelo.setIdUsuario(100);
        vuelo.setTipoVuelo("Internacional");
        vuelo.setProveedor("Avianca");
        vuelo.setDescripcion("Vuelo directo");
        vuelo.setDisponibilidadServicio(true);
        vuelo.setCategoria("Turismo");
    }
    
    @Test
    @DisplayName("Test obtener todos los vuelos")
    void testObtenerTodosLosVuelos() {
        List<Vuelo> vuelos = Arrays.asList(vuelo, new Vuelo());
        when(vueloRepository.findAll()).thenReturn(vuelos);
        
        List<Vuelo> resultado = vueloService.obtenerTodosLosVuelos();
        
        assertEquals(2, resultado.size());
        verify(vueloRepository, times(1)).findAll();
    }
    
    @Test
    @DisplayName("Test obtener vuelo por ID existente")
    void testObtenerVueloPorIdExistente() {
        when(vueloRepository.findById(1)).thenReturn(Optional.of(vuelo));
        
        Vuelo resultado = vueloService.obtenerVueloPorId(1);
        
        assertNotNull(resultado);
        assertEquals(1, resultado.getIdVuelo());
        verify(vueloRepository, times(1)).findById(1);
    }
    
    @Test
    @DisplayName("Test obtener vuelo por ID inexistente")
    void testObtenerVueloPorIdInexistente() {
        when(vueloRepository.findById(999)).thenReturn(Optional.empty());
        
        Vuelo resultado = vueloService.obtenerVueloPorId(999);
        
        assertNull(resultado);
        verify(vueloRepository, times(1)).findById(999);
    }
    
    @Test
    @DisplayName("Test guardar vuelo")
    void testGuardarVuelo() {
        when(vueloRepository.save(vuelo)).thenReturn(vuelo);
        
        Vuelo resultado = vueloService.guardarVuelo(vuelo);
        
        assertNotNull(resultado);
        assertEquals(vuelo.getIdVuelo(), resultado.getIdVuelo());
        verify(vueloRepository, times(1)).save(vuelo);
    }
    
    @Test
    @DisplayName("Test eliminar vuelo")
    void testEliminarVuelo() {
        doNothing().when(vueloRepository).deleteById(1);
        
        vueloService.eliminarVuelo(1);
        
        verify(vueloRepository, times(1)).deleteById(1);
    }
    
    @Test
    @DisplayName("Test obtener vuelos por usuario")
    void testObtenerVuelosPorUsuario() {
        List<Vuelo> vuelos = Arrays.asList(vuelo);
        when(vueloRepository.findByIdUsuario(100)).thenReturn(vuelos);
        
        List<Vuelo> resultado = vueloService.obtenerVuelosPorUsuario(100);
        
        assertEquals(1, resultado.size());
        assertEquals(100, resultado.get(0).getIdUsuario());
        verify(vueloRepository, times(1)).findByIdUsuario(100);
    }
    
    @Test
    @DisplayName("Test obtener vuelos por categoría")
    void testObtenerVuelosPorCategoria() {
        List<Vuelo> vuelos = Arrays.asList(vuelo);
        when(vueloRepository.findByCategoria("Turismo")).thenReturn(vuelos);
        
        List<Vuelo> resultado = vueloService.obtenerVuelosPorCategoria("Turismo");
        
        assertEquals(1, resultado.size());
        assertEquals("Turismo", resultado.get(0).getCategoria());
        verify(vueloRepository, times(1)).findByCategoria("Turismo");
    }
    
    @Test
    @DisplayName("Test obtener vuelos disponibles")
    void testObtenerVuelosDisponibles() {
        List<Vuelo> vuelos = Arrays.asList(vuelo);
        when(vueloRepository.findByDisponibilidadServicioTrue()).thenReturn(vuelos);
        
        List<Vuelo> resultado = vueloService.obtenerVuelosDisponibles();
        
        assertEquals(1, resultado.size());
        assertTrue(resultado.get(0).isDisponibilidadServicio());
        verify(vueloRepository, times(1)).findByDisponibilidadServicioTrue();
    }
    
    @Test
    @DisplayName("Test obtener vuelos por fechas")
    void testObtenerVuelosPorFechas() {
        Date inicio = Date.valueOf("2024-01-01");
        Date fin = Date.valueOf("2024-01-31");
        List<Vuelo> vuelos = Arrays.asList(vuelo);
        
        when(vueloRepository.findByFechaInicioBetween(inicio, fin)).thenReturn(vuelos);
        
        List<Vuelo> resultado = vueloService.obtenerVuelosPorFechas(inicio, fin);
        
        assertEquals(1, resultado.size());
        verify(vueloRepository, times(1)).findByFechaInicioBetween(inicio, fin);
    }
}