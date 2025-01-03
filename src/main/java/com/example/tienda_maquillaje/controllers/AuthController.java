package com.example.tienda_maquillaje.controllers;

import com.example.tienda_maquillaje.entities.User;
import com.example.tienda_maquillaje.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }



    // Método para manejar el cierre de sesión
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        // Limpiar el contexto de seguridad
        SecurityContextHolder.clearContext();

        // Preparar respuesta
        Map<String, String> response = new HashMap<>();
        response.put("message", "Sesión cerrada exitosamente");

        // Retornar respuesta de éxito
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String role = request.get("role");

        boolean isRegistered = authService.register(username, password, role);

        Map<String, String> response = new HashMap<>();
        if (isRegistered) {
            response.put("message", "Usuario registrado con éxito");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "El registro falló. El usuario ya existe.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Optional<User> user = authService.authenticate(username, password);
        Map<String, String> response = new HashMap<>();

        if (user.isPresent()) {
            response.put("message", "Inicio de sesión exitoso");
            response.put("role", user.get().getRole());
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
