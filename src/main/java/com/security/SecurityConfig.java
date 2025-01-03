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
import org.springframework.security.core.context.SecurityContextHolder;


@Configuration
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Mantener contraseñas sin encriptar
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and().csrf().disable() // Habilitar CORS y desactivar CSRF
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/auth/**", "/public/**", "/productos/**").permitAll() // Rutas públicas
                .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
            )
            .formLogin() // Formulario básico de inicio de sesión
                .loginPage("/login")
                .permitAll()
            .and()
            .logout() // Configurar logout
                .logoutUrl("/logout") // URL para la acción de logout
                .logoutSuccessUrl("/login") // Redirige al login después del logout
                .invalidateHttpSession(true) // Invalidar la sesión
                .clearAuthentication(true); // Limpiar la autenticación
        return http.build();
    }

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

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(username -> userRepository.findByUsername(username)
                .map(user -> org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole()) // Configura el rol del usuario
                    .build()
                )
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username))
            )
            .and()
            .build();
    }
}
