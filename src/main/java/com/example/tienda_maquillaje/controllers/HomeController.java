package com.example.tienda_maquillaje.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador principal de la aplicación.
 * 
 * Este controlador maneja las solicitudes a la raíz de la aplicación.
 */
@RestController
public class HomeController {

    /**
     * Endpoint para la página de inicio.
     * 
     * Maneja las solicitudes GET al endpoint raíz ("/").
     * 
     * @return Un mensaje de bienvenida como respuesta.
     */
    @GetMapping("/")
    public String home() {
        // Retorna un mensaje de bienvenida al usuario.
        return "Bienvenido a la tienda de maquillaje";
    }
}
