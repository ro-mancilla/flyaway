package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import com.example.demo.model.Vuelo;
import com.example.demo.service.VueloService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(VueloController.class)
@DisplayName("VueloController Integration Tests")
class VueloControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private VueloService vueloService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
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
    @DisplayName("Test GET /api/v1/vuelo - obtener todos los vuelos")
    void testObtenerTodos() throws Exception {
        List<Vuelo> vuelos = Arrays.asList(vuelo);
        when(vueloService.obtenerTodosLosVuelos()).thenReturn(vuelos);
        
        mockMvc.perform(get("/api/v1/vuelo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].idVuelo").value(1))
                .andExpect(jsonPath("$[0].proveedor").value("Avianca"));
    }
    
    @Test
    @DisplayName("Test GET /api/v1/vuelo/{id} - obtener vuelo por ID")
    void testObtenerPorId() throws Exception {
        when(vueloService.obtenerVueloPorId(1)).thenReturn(vuelo);
        
        mockMvc.perform(get("/api/v1/vuelo/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idVuelo").value(1))
                .andExpect(jsonPath("$.proveedor").value("Avianca"));
    }
    
    @Test
    @DisplayName("Test POST /api/v1/vuelo - crear nuevo vuelo")
    void testCrearVuelo() throws Exception {
        when(vueloService.guardarVuelo(any(Vuelo.class))).thenReturn(vuelo);
        
        mockMvc.perform(post("/api/v1/vuelo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vuelo)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idVuelo").value(1))
                .andExpect(jsonPath("$.proveedor").value("Avianca"));
    }
    
    @Test
    @DisplayName("Test PUT /api/v1/vuelo/{id} - actualizar vuelo")
    void testActualizarVuelo() throws Exception {
        when(vueloService.guardarVuelo(any(Vuelo.class))).thenReturn(vuelo);
        
        mockMvc.perform(put("/api/v1/vuelo/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vuelo)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    
    @Test
    @DisplayName("Test DELETE /api/v1/vuelo/{id} - eliminar vuelo")
    void testEliminarVuelo() throws Exception {
        doNothing().when(vueloService).eliminarVuelo(1);
        
        mockMvc.perform(delete("/api/v1/vuelo/1"))
                .andExpect(status().isOk());
        
        verify(vueloService, times(1)).eliminarVuelo(1);
    }
    
    @Test
    @DisplayName("Test GET /api/v1/vuelo/usuario/{idUsuario} - obtener vuelos por usuario")
    void testObtenerPorUsuario() throws Exception {
        List<Vuelo> vuelos = Arrays.asList(vuelo);
        when(vueloService.obtenerVuelosPorUsuario(100)).thenReturn(vuelos);
        
        mockMvc.perform(get("/api/v1/vuelo/usuario/100"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].idUsuario").value(100));
    }
    
    @Test
    @DisplayName("Test GET /api/v1/vuelo/categoria/{categoria} - obtener vuelos por categoría")
    void testObtenerPorCategoria() throws Exception {
        List<Vuelo> vuelos = Arrays.asList(vuelo);
        when(vueloService.obtenerVuelosPorCategoria("Turismo")).thenReturn(vuelos);
        
        mockMvc.perform(get("/api/v1/vuelo/categoria/Turismo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].categoria").value("Turismo"));
    }
    
    @Test
    @DisplayName("Test GET /api/v1/vuelo/disponibles - obtener vuelos disponibles")
    void testObtenerDisponibles() throws Exception {
        List<Vuelo> vuelos = Arrays.asList(vuelo);
        when(vueloService.obtenerVuelosDisponibles()).thenReturn(vuelos);
        
        mockMvc.perform(get("/api/v1/vuelo/disponibles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].disponibilidadServicio").value(true));
    }
    
    @Test
    @DisplayName("Test GET /api/v1/vuelo/fechas - obtener vuelos por fechas")
    void testObtenerPorFechas() throws Exception {
        List<Vuelo> vuelos = Arrays.asList(vuelo);
        when(vueloService.obtenerVuelosPorFechas(any(Date.class), any(Date.class)))
                .thenReturn(vuelos);
        
        mockMvc.perform(get("/api/v1/vuelo/fechas")
                .param("inicio", "2024-01-01")
                .param("fin", "2024-01-31"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}