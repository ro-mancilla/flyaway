package com.example.demo.performance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.model.Vuelo;
import com.example.demo.repository.VueloRepository;
import com.example.demo.service.VueloService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("Vuelo Performance Tests")
class VueloPerformanceTest {
    
    @Autowired
    private VueloService vueloService;
    
    @Autowired
    private VueloRepository vueloRepository;
    
    @BeforeEach
    void setUp() {
        // Limpiar datos existentes
        vueloRepository.deleteAll();
    }
    
    @Test
    @DisplayName("Test rendimiento - inserción masiva de vuelos")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testInsertMasivoVuelos() {
        int cantidadVuelos = 1000;
        List<Vuelo> vuelos = new ArrayList<>();
        
        long inicio = System.currentTimeMillis();
        
        // Crear vuelos en memoria
        for (int i = 0; i < cantidadVuelos; i++) {
            Vuelo vuelo = new Vuelo();
            vuelo.setIdUsuario(i % 100 + 1);
            vuelo.setTipoVuelo(i % 2 == 0 ? "Nacional" : "Internacional");
            vuelo.setFechaInicio(Date.valueOf("2024-01-01"));
            vuelo.setFechaFinal(Date.valueOf("2024-01-02"));
            vuelo.setProveedor("Proveedor" + (i % 5));
            vuelo.setDescripcion("Descripción vuelo " + i);
            vuelo.setDisponibilidadServicio(i % 2 == 0);
            vuelo.setCategoria(i % 3 == 0 ? "Turismo" : i % 3 == 1 ? "Negocios" : "Primera");
            vuelos.add(vuelo);
        }
        
        // Insertar en batch
        vueloRepository.saveAll(vuelos);
        
        long fin = System.currentTimeMillis();
        long tiempoEjecucion = fin - inicio;
        
        System.out.println("Tiempo de inserción de " + cantidadVuelos + " vuelos: " + tiempoEjecucion + "ms");
        
        // Verificar que se insertaron correctamente
        assertEquals(cantidadVuelos, vueloRepository.count());
        
        // Verificar que el tiempo no exceda el límite esperado (ej: 8 segundos)
        assertTrue(tiempoEjecucion < 8000, "La inserción tardó más de 8 segundos: " + tiempoEjecucion + "ms");
    }
    
    @Test
    @DisplayName("Test rendimiento - consulta de todos los vuelos")
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testConsultaTodosLosVuelos() {
        // Insertar datos de prueba
        insertarDatosPrueba(500);
        
        long inicio = System.currentTimeMillis();
        
        List<Vuelo> vuelos = vueloService.obtenerTodosLosVuelos();
        
        long fin = System.currentTimeMillis();
        long tiempoEjecucion = fin - inicio;
        
        System.out.println("Tiempo de consulta de todos los vuelos: " + tiempoEjecucion + "ms");
        
        assertFalse(vuelos.isEmpty());
        assertTrue(tiempoEjecucion < 3000, "La consulta tardó más de 3 segundos: " + tiempoEjecucion + "ms");
    }
    
    @Test
    @DisplayName("Test rendimiento - búsqueda por usuario")
    @Timeout(value = 3, unit = TimeUnit.SECONDS)
    void testBusquedaPorUsuario() {
        insertarDatosPrueba(1000);
        
        long inicio = System.currentTimeMillis();
        
        List<Vuelo> vuelos = vueloService.obtenerVuelosPorUsuario(50);
        
        long fin = System.currentTimeMillis();
        long tiempoEjecucion = fin - inicio;
        
        System.out.println("Tiempo de búsqueda por usuario: " + tiempoEjecucion + "ms");
        
        assertTrue(tiempoEjecucion < 2000, "La búsqueda tardó más de 2 segundos: " + tiempoEjecucion + "ms");
    }
    
    @Test
    @DisplayName("Test rendimiento - múltiples consultas concurrentes")
    void testConsultasConcurrentes() throws InterruptedException {
        insertarDatosPrueba(200);
        
        int numThreads = 10;
        Thread[] threads = new Thread[numThreads];
        long[] tiempos = new long[numThreads];
        
        for (int i = 0; i < numThreads; i++) {
            final int threadIndex = i;
            threads[i] = new Thread(() -> {
                long inicio = System.currentTimeMillis();
                vueloService.obtenerTodosLosVuelos();
                tiempos[threadIndex] = System.currentTimeMillis() - inicio;
            });
        }
        
        long inicioTotal = System.currentTimeMillis();
        
        // Iniciar todos los threads
        for (Thread thread : threads) {
            thread.start();
        }
        
        // Esperar que terminen todos
        for (Thread thread : threads) {
            thread.join();
        }
        
        long finTotal = System.currentTimeMillis();
        long tiempoTotal = finTotal - inicioTotal;
        
        System.out.println("Tiempo total para " + numThreads + " consultas concurrentes: " + tiempoTotal + "ms");
        
        // Verificar que el tiempo total no sea excesivo
        assertTrue(tiempoTotal < 10000, "Las consultas concurrentes tardaron más de 10 segundos");
        
        // Verificar que cada consulta individual no tardó demasiado
        for (long tiempo : tiempos) {
            assertTrue(tiempo < 5000, "Una consulta individual tardó más de 5 segundos: " + tiempo + "ms");
        }
    }
    
    @Test
    @DisplayName("Test memoria - verificar no hay memory leaks")
    void testMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        
        // Forzar garbage collection
        runtime.gc();
        long memoriaInicial = runtime.totalMemory() - runtime.freeMemory();
        
        // Realizar operaciones que podrían causar memory leaks
        for (int i = 0; i < 100; i++) {
            List<Vuelo> vuelos = new ArrayList<>();
            for (int j = 0; j < 50; j++) {
                Vuelo vuelo = new Vuelo();
                vuelo.setIdUsuario(j);
                vuelo.setProveedor("Test Provider");
                vuelo.setDescripcion("Test Description");
                vuelo.setDisponibilidadServicio(true);
                vuelo.setCategoria("Test");
                vuelos.add(vuelo);
            }
            // Simular trabajo con los vuelos
            vuelos.clear();
        }
        
        // Forzar garbage collection nuevamente
        runtime.gc();
        long memoriaFinal = runtime.totalMemory() - runtime.freeMemory();
        
        long incrementoMemoria = memoriaFinal - memoriaInicial;
        
        System.out.println("Incremento de memoria: " + incrementoMemoria + " bytes");
        
        // Verificar que el incremento de memoria no sea excesivo (menos de 50MB)
        assertTrue(incrementoMemoria < 50 * 1024 * 1024, 
                   "Posible memory leak detectado. Incremento: " + incrementoMemoria + " bytes");
    }
    
    private void insertarDatosPrueba(int cantidad) {
        List<Vuelo> vuelos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Vuelo vuelo = new Vuelo();
            vuelo.setIdUsuario(i % 100 + 1);
            vuelo.setTipoVuelo("Test");
            vuelo.setProveedor("TestProvider");
            vuelo.setDescripcion("Test Description");
            vuelo.setDisponibilidadServicio(true);
            vuelo.setCategoria("Test");
            vuelos.add(vuelo);
        }
        vueloRepository.saveAll(vuelos);
    }
}