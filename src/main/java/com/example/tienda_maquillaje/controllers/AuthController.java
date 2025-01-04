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

/**
 * Controlador para la autenticación y gestión de usuarios.
 * 
 * Maneja los endpoints relacionados con el registro, inicio de sesión y cierre de sesión.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    // Servicio de autenticación utilizado por el controlador
    private final AuthService authService;

    /**
     * Constructor del controlador.
     * 
     * @param authService Servicio de autenticación que proporciona la lógica necesaria.
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint para manejar el cierre de sesión.
     * 
     * Este método limpia el contexto de seguridad, cerrando la sesión del usuario actual.
     * 
     * @return ResponseEntity con un mensaje de éxito en el cierre de sesión.
     */
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

    /**
     * Endpoint para registrar un nuevo usuario.
     * 
     * Este método recibe un objeto JSON con los datos de usuario (nombre, contraseña y rol)
     * y utiliza el servicio de autenticación para registrar al usuario.
     * 
     * @param request Un mapa que contiene los datos del usuario (username, password, role).
     * @return ResponseEntity con un mensaje de éxito o error en el registro.
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> request) {
        // Extraer datos del request
        String username = request.get("username");
        String password = request.get("password");
        String role = request.get("role");

        // Intentar registrar al usuario
        boolean isRegistered = authService.register(username, password, role);

        // Preparar respuesta
        Map<String, String> response = new HashMap<>();
        if (isRegistered) {
            response.put("message", "Usuario registrado con éxito");
            return ResponseEntity.ok(response); // 200 OK
        } else {
            response.put("message", "El registro falló. El usuario ya existe.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // 400 BAD REQUEST
        }
    }

    /**
     * Endpoint para el inicio de sesión.
     * 
     * Este método autentica al usuario con su nombre y contraseña, y devuelve un mensaje
     * de éxito o error junto con el rol del usuario en caso de autenticación exitosa.
     * 
     * @param request Un mapa que contiene las credenciales del usuario (username, password).
     * @return ResponseEntity con un mensaje de éxito o error en el inicio de sesión.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
        // Extraer credenciales del request
        String username = request.get("username");
        String password = request.get("password");

        // Intentar autenticar al usuario
        Optional<User> user = authService.authenticate(username, password);
        Map<String, String> response = new HashMap<>();

        if (user.isPresent()) {
            // Si la autenticación es exitosa, devolver mensaje de éxito y el rol del usuario
            response.put("message", "Inicio de sesión exitoso");
            response.put("role", user.get().getRole());
            return ResponseEntity.ok(response); // 200 OK
        } else {
            // Si falla la autenticación, devolver mensaje de error
            response.put("message", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // 401 UNAUTHORIZED
        }
    }
}
