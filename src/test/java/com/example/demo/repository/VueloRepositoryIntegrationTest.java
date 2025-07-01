package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.model.Vuelo;

import java.sql.Date;
import java.util.List;

@DataJpaTest
@Testcontainers
@DisplayName("VueloRepository Integration Tests")
class VueloRepositoryIntegrationTest {
    
    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private VueloRepository vueloRepository;
    
    private Vuelo vuelo1;
    private Vuelo vuelo2;
    
    @BeforeEach
    void setUp() {
        vuelo1 = new Vuelo();
        vuelo1.setIdUsuario(100);
        vuelo1.setTipoVuelo("Internacional");
        vuelo1.setFechaInicio(Date.valueOf("2024-01-01"));
        vuelo1.setFechaFinal(Date.valueOf("2024-01-02"));
        vuelo1.setProveedor("Avianca");
        vuelo1.setDescripcion("Vuelo directo");
        vuelo1.setDisponibilidadServicio(true);
        vuelo1.setCategoria("Turismo");
        
        vuelo2 = new Vuelo();
        vuelo2.setIdUsuario(200);
        vuelo2.setTipoVuelo("Nacional");
        vuelo2.setFechaInicio(Date.valueOf("2024-02-01"));
        vuelo2.setFechaFinal(Date.valueOf("2024-02-02"));
        vuelo2.setProveedor("LATAM");
        vuelo2.setDescripcion("Vuelo con escalas");
        vuelo2.setDisponibilidadServicio(false);
        vuelo2.setCategoria("Negocios");
        
        entityManager.persistAndFlush(vuelo1);
        entityManager.persistAndFlush(vuelo2);
    }
    
    @Test
    @DisplayName("Test findByIdUsuario")
    void testFindByIdUsuario() {
        List<Vuelo> vuelos = vueloRepository.findByIdUsuario(100);
        
        assertThat(vuelos).hasSize(1);
        assertThat(vuelos.get(0).getIdUsuario()).isEqualTo(100);
        assertThat(vuelos.get(0).getProveedor()).isEqualTo("Avianca");
    }
    
    @Test
    @DisplayName("Test findByCategoria")
    void testFindByCategoria() {
        List<Vuelo> vuelos = vueloRepository.findByCategoria("Turismo");
        
        assertThat(vuelos).hasSize(1);
        assertThat(vuelos.get(0).getCategoria()).isEqualTo("Turismo");
        assertThat(vuelos.get(0).getProveedor()).isEqualTo("Avianca");
    }
    
    @Test
    @DisplayName("Test findByDisponibilidadServicioTrue")
    void testFindByDisponibilidadServicioTrue() {
        List<Vuelo> vuelos = vueloRepository.findByDisponibilidadServicioTrue();
        
        assertThat(vuelos).hasSize(1);
        assertThat(vuelos.get(0).isDisponibilidadServicio()).isTrue();
        assertThat(vuelos.get(0).getProveedor()).isEqualTo("Avianca");
    }
    
    @Test
    @DisplayName("Test findByFechaInicioBetween")
    void testFindByFechaInicioBetween() {
        Date inicio = Date.valueOf("2023-12-01");
        Date fin = Date.valueOf("2024-01-31");
        
        List<Vuelo> vuelos = vueloRepository.findByFechaInicioBetween(inicio, fin);
        
        assertThat(vuelos).hasSize(1);
        assertThat(vuelos.get(0).getFechaInicio()).isEqualTo(Date.valueOf("2024-01-01"));
    }
    
    @Test
    @DisplayName("Test save and findById")
    void testSaveAndFindById() {
        Vuelo nuevoVuelo = new Vuelo();
        nuevoVuelo.setIdUsuario(300);
        nuevoVuelo.setTipoVuelo("Charter");
        nuevoVuelo.setProveedor("Copa Airlines");
        nuevoVuelo.setDescripcion("Vuelo charter");
        nuevoVuelo.setDisponibilidadServicio(true);
        nuevoVuelo.setCategoria("Lujo");
        
        Vuelo vueloGuardado = vueloRepository.save(nuevoVuelo);
        
        assertThat(vueloGuardado.getIdVuelo()).isNotNull();
        assertThat(vueloRepository.findById(vueloGuardado.getIdVuelo())).isPresent();
    }
    
    @Test
    @DisplayName("Test delete")
    void testDelete() {
        Integer id = vuelo1.getIdVuelo();
        vueloRepository.deleteById(id);
        
        assertThat(vueloRepository.findById(id)).isEmpty();
    }
    
    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        List<Vuelo> vuelos = vueloRepository.findAll();
        
        assertThat(vuelos).hasSize(2);
    }
}