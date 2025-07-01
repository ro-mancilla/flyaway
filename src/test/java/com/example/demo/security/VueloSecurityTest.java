package com.example.demo.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import com.example.demo.model.Vuelo;
import com.example.demo.service.VueloService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureWebMvc
@DisplayName("Vuelo Security Tests")
class VueloSecurityTest {
    
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext context;
    
    @MockBean
    private VueloService vueloService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private Vuelo vuelo;
    
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
                
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
    @DisplayName("Test acceso sin autenticación - debe fallar")
    @WithAnonymousUser
    void testAccesoSinAutenticacion() throws Exception {
        mockMvc.perform(get("/api/v1/vuelo"))
                .andExpect(status().isUnauthorized());
    }
    
    @Test
    @DisplayName("Test acceso con usuario autenticado - debe funcionar")
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testAccesoConUsuarioAutenticado() throws Exception {
        List<Vuelo> vuelos = Arrays.asList(vuelo);
        when(vueloService.obtenerTodosLosVuelos()).thenReturn(vuelos);
        
        mockMvc.perform(get("/api/v1/vuelo"))
                .andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("Test acceso con rol ADMIN - debe funcionar")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testAccesoConRolAdmin() throws Exception {
        List<Vuelo> vuelos = Arrays.asList(vuelo);
        when(vueloService.obtenerTodosLosVuelos()).thenReturn(vuelos);
        
        mockMvc.perform(get("/api/v1/vuelo"))
                .andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("Test creación de vuelo sin autorización")
    @WithAnonymousUser
    void testCreacionSinAutorizacion() throws Exception {
        mockMvc.perform(post("/api/v1/vuelo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vuelo)))
                .andExpect(status().isUnauthorized());
    }
    
    @Test
    @DisplayName("Test eliminación con rol USER - debe fallar")
    @WithMockUser(username = "user", roles = {"USER"})
    void testEliminacionConRolUser() throws Exception {
        // Asumiendo que solo ADMIN puede eliminar
        mockMvc.perform(delete("/api/v1/vuelo/1"))
                .andExpect(status().isForbidden());
    }
    
    @Test
    @DisplayName("Test eliminación con rol ADMIN - debe funcionar")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testEliminacionConRolAdmin() throws Exception {
        doNothing().when(vueloService).eliminarVuelo(1);
        
        mockMvc.perform(delete("/api/v1/vuelo/1"))
                .andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("Test inyección SQL en parámetros")
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testInyeccionSQL() throws Exception {
        String maliciousInput = "1'; DROP TABLE vuelo; --";
        
        // Test en path variable
        mockMvc.perform(get("/api/v1/vuelo/" + maliciousInput))
                .andExpect(status().isBadRequest());
        
        // Test en parámetros de consulta
        mockMvc.perform(get("/api/v1/vuelo/categoria/" + maliciousInput))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    @DisplayName("Test XSS en campos de texto")
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testXSSPrevention() throws Exception {
        Vuelo vueloMalicioso = new Vuelo();
        vueloMalicioso.setIdUsuario(100);
        vueloMalicioso.setTipoVuelo("<script>alert('XSS')</script>");
        vueloMalicioso.setProveedor("<img src=x onerror=alert('XSS')>");
        vueloMalicioso.setDescripcion("javascript:alert('XSS')");
        vueloMalicioso.setDisponibilidadServicio(true);
        vueloMalicioso.setCategoria("Test");
        
        when(vueloService.guardarVuelo(any(Vuelo.class))).thenReturn(vueloMalicioso);
        
        mockMvc.perform(post("/api/v1/vuelo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vueloMalicioso)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoVuelo").value(not(containsString("<script>"))));
    }
    
    @Test
    @DisplayName("Test validación de entrada - campos requeridos")
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testValidacionCamposRequeridos() throws Exception {
        Vuelo vueloInvalido = new Vuelo();
        // No se establecen campos requeridos
        
        mockMvc.perform(post("/api/v1/vuelo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vueloInvalido)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    @DisplayName("Test rate limiting - múltiples requests")
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testRateLimiting() throws Exception {
        List<Vuelo> vuelos = Arrays.asList(vuelo);
        when(vueloService.obtenerTodosLosVuelos()).thenReturn(vuelos);
        
        // Simular múltiples requests rápidos
        for (int i = 0; i < 100; i++) {
            mockMvc.perform(get("/api/v1/vuelo"))
                    .andExpect(status().isOk());
        }
        
        // El request 101 podría ser bloqueado por rate limiting
        // (esto dependería de la configuración real de rate limiting)
    }
    
    @Test
    @DisplayName("Test CSRF protection")
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testCSRFProtection() throws Exception {
        // Test que los endpoints POST/PUT/DELETE requieren token CSRF
        mockMvc.perform(post("/api/v1/vuelo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vuelo)))
                .andExpect(status().isForbidden()); // Sin CSRF token
    }
    
    @Test
    @DisplayName("Test autorización por propietario de vuelo")
    @WithMockUser(username = "user100", roles = {"USER"})
    void testAutorizacionPorPropietario() throws Exception {
        // Usuario solo puede acceder a sus propios vuelos
        when(vueloService.obtenerVuelosPorUsuario(100)).thenReturn(Arrays.asList(vuelo));
        when(vueloService.obtenerVuelosPorUsuario(200)).thenReturn(Arrays.asList());
        
        // Acceso a propios vuelos - debe funcionar
        mockMvc.perform(get("/api/v1/vuelo/usuario/100"))
                .andExpect(status().isOk());
        
        // Acceso a vuelos de otro usuario - debe fallar
        mockMvc.perform(get("/api/v1/vuelo/usuario/200"))
                .andExpect(status().isForbidden());
    }
    
    private static org.hamcrest.Matcher<String> containsString(String substring) {
        return org.hamcrest.CoreMatchers.containsString(substring);
    }
    
    private static org.hamcrest.Matcher<String> not(org.hamcrest.Matcher<String> matcher) {
        return org.hamcrest.CoreMatchers.not(matcher);
    }
}