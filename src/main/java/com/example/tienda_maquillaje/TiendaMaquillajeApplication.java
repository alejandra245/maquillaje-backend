package com.example.tienda_maquillaje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Tienda Maquillaje.
 * Esta clase inicia la aplicación Spring Boot.
 * 
 * Anotación @SpringBootApplication:
 * - Marca esta clase como la clase principal de configuración.
 * - Combina tres anotaciones clave:
 *   1. @Configuration: Marca esta clase como una fuente de configuración.
 *   2. @EnableAutoConfiguration: Habilita la configuración automática de Spring Boot.
 *   3. @ComponentScan: Escanea los componentes, controladores y servicios en el paquete base.
 */
@SpringBootApplication
public class TiendaMaquillajeApplication {

    /**
     * Método principal de la aplicación.
     * 
     * @param args Argumentos pasados al ejecutar la aplicación.
     * 
     * El método llama a {@link SpringApplication#run(Class, String[])}
     * para iniciar el contexto de la aplicación Spring.
     */
    public static void main(String[] args) {
        // Inicia la aplicación Spring Boot.
        SpringApplication.run(TiendaMaquillajeApplication.class, args);
    }
}
