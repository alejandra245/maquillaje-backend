package com.example.tienda_maquillaje;

/**
 * Clase que representa la respuesta de un intento de inicio de sesión.
 * 
 * Contiene información sobre el resultado del inicio de sesión, incluyendo
 * un mensaje descriptivo y el rol del usuario autenticado.
 */
public class LoginResponse {

    /**
     * Mensaje que describe el resultado del inicio de sesión.
     * Por ejemplo: "Inicio de sesión exitoso" o "Credenciales inválidas".
     */
    private String message;

    /**
     * Rol del usuario que inició sesión.
     * Por ejemplo: "usuario" o "trabajador".
     */
    private String role;

    /**
     * Constructor para inicializar una respuesta de inicio de sesión.
     * 
     * @param message Mensaje descriptivo del resultado.
     * @param role Rol del usuario autenticado.
     */
    public LoginResponse(String message, String role) {
        this.message = message;
        this.role = role;
    }

    /**
     * Obtiene el mensaje de la respuesta.
     * 
     * @return El mensaje descriptivo del resultado del inicio de sesión.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Establece el mensaje de la respuesta.
     * 
     * @param message El mensaje descriptivo del resultado.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Obtiene el rol del usuario autenticado.
     * 
     * @return El rol del usuario (e.g., "usuario" o "trabajador").
     */
    public String getRole() {
        return role;
    }

    /**
     * Establece el rol del usuario autenticado.
     * 
     * @param role El rol del usuario (e.g., "usuario" o "trabajador").
     */
    public void setRole(String role) {
        this.role = role;
    }
}
