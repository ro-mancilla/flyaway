package com.example.demo.suite;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.platform.suite.api.IncludeClassNamePatterns;

@Suite
@SuiteDisplayName("Plan de Pruebas Completo para Vuelo")
@SelectPackages({
    "com.example.demo.model",
    "com.example.demo.service", 
    "com.example.demo.controller",
    "com.example.demo.repository",
    "com.example.demo.performance",
    "com.example.demo.security"
})
@IncludeClassNamePatterns(".*Test")
public class VueloTestSuite {
    
    /*
     * PLAN DE PRUEBAS COMPLETO PARA LA CLASE VUELO
     * 
     * Este suite ejecuta todas las pruebas organizadas en las siguientes categorías:
     * 
     * 1. PRUEBAS UNITARIAS (Unit Tests):
     *    - VueloTest: Pruebas del modelo/entidad Vuelo
     *    - VueloServiceTest: Pruebas del servicio con mocks
     * 
     * 2. PRUEBAS DE INTEGRACIÓN (Integration Tests):
     *    - VueloControllerTest: Pruebas del controlador REST con MockMvc
     *    - VueloRepositoryIntegrationTest: Pruebas del repositorio con TestContainers
     * 
     * 3. PRUEBAS DE RENDIMIENTO (Performance Tests):
     *    - VueloPerformanceTest: Pruebas de carga, tiempo de respuesta y memoria
     * 
     * 4. PRUEBAS DE SEGURIDAD (Security Tests):
     *    - VueloSecurityTest: Pruebas de autenticación, autorización, XSS, SQL injection
     * 
     * COBERTURA DE PRUEBAS:
     * - Funcionalidad básica CRUD
     * - Búsquedas y filtros
     * - Validaciones de datos
     * - Manejo de errores
     * - Rendimiento bajo carga
     * - Seguridad y autorización
     * - Inyección de dependencias
     * - Persistencia de datos
     * 
     * TECNOLOGÍAS UTILIZADAS:
     * - JUnit 5
     * - Mockito
     * - Spring Boot Test
     * - TestContainers
     * - Spring Security Test
     * - MockMvc
     * - H2 Database (para pruebas)
     * - MySQL TestContainer (para integración)
     * 
     * EJECUTAR PRUEBAS:
     * mvn test (todas las pruebas)
     * mvn test -Dtest=VueloTestSuite (solo este suite)
     * mvn test -Dtest=VueloPerformanceTest (solo rendimiento)
     * mvn test -Dtest=VueloSecurityTest (solo seguridad)
     */
}