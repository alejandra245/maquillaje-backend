package com.example.tienda_maquillaje.security;

import com.example.tienda_maquillaje.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuración de seguridad para la aplicación.
 * 
 * Define las políticas de acceso, autenticación, CORS y manejo de sesiones.
 */
@Configuration
public class SecurityConfig {

    // Repositorio de usuarios para buscar credenciales
    private final UserRepository userRepository;

    /**
     * Constructor de la configuración de seguridad.
     * 
     * @param userRepository Repositorio para manejar los usuarios.
     */
    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Bean para configurar el codificador de contraseñas.
     * 
     * Nota: NoOpPasswordEncoder se utiliza aquí para mantener las contraseñas sin encriptar,
     * pero no es recomendado en entornos de producción.
     * 
     * @return Codificador de contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Configuración del filtro de seguridad HTTP.
     * 
     * Configura las políticas de acceso, autenticación y logout.
     * 
     * @param http Configuración de seguridad HTTP.
     * @return La cadena de filtros de seguridad configurada.
     * @throws Exception Si hay un error durante la configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and().csrf().disable() // Habilitar CORS y desactivar CSRF
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/auth/**", "/public/**", "/productos/**").permitAll() // Rutas públicas
                .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
            )
            .formLogin() // Configura un formulario básico de inicio de sesión
                .loginPage("/login") // Página personalizada de login
                .permitAll() // Permitir acceso público al login
            .and()
            .logout() // Configuración de logout
                .logoutUrl("/logout") // URL para la acción de logout
                .logoutSuccessUrl("/login") // Redirige al login después de cerrar sesión
                .invalidateHttpSession(true) // Invalidar la sesión actual
                .clearAuthentication(true); // Limpiar autenticación del usuario
        return http.build();
    }

    /**
     * Configuración del filtro CORS.
     * 
     * Permite solicitudes de cualquier origen, método y encabezado, 
     * y habilita el uso de cookies o credenciales.
     * 
     * @return El filtro CORS configurado.
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOriginPattern("*"); // Permite cualquier origen
        corsConfig.addAllowedMethod("*");       // Permite cualquier método HTTP (GET, POST, etc.)
        corsConfig.addAllowedHeader("*");       // Permite cualquier encabezado
        corsConfig.setAllowCredentials(true);   // Permite cookies o credenciales

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig); // Aplica configuración CORS a todas las rutas
        return new CorsFilter(source);
    }

    /**
     * Configuración del manejador de autenticación.
     * 
     * Este bean utiliza el repositorio de usuarios para validar las credenciales 
     * y establecer roles de usuario.
     * 
     * @param http Configuración de seguridad HTTP.
     * @return El manejador de autenticación configurado.
     * @throws Exception Si hay un error durante la configuración.
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(username -> userRepository.findByUsername(username)
                .map(user -> org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername()) // Configura el nombre de usuario
                    .password(user.getPassword())    // Configura la contraseña
                    .roles(user.getRole())           // Configura los roles
                    .build()
                )
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username))
            )
            .and()
            .build();
    }
}
