package com.example.tienda_maquillaje.RegistreRequest;

/**
 * Clase que representa la solicitud de registro de un usuario.
 * 
 * Contiene los datos necesarios para registrar un nuevo usuario en el sistema:
 * - Nombre de usuario (username).
 * - Contraseña (password).
 * - Rol del usuario (role).
 */
public class RegisterRequest {

    /**
     * Nombre de usuario del nuevo usuario.
     */
    private String username;

    /**
     * Contraseña del nuevo usuario.
     */
    private String password;

    /**
     * Rol del nuevo usuario.
     * Ejemplos de valores:
     * - "usuario": Cliente o consumidor.
     * - "trabajador": Personal o administrador del sistema.
     */
    private String role;

    // Métodos Getters y Setters

    /**
     * Obtiene el nombre de usuario.
     * 
     * @return El nombre de usuario ingresado.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     * 
     * @param username El nombre de usuario que se desea asignar.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return La contraseña ingresada.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password La contraseña que se desea asignar.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el rol del usuario.
     * 
     * @return El rol del usuario (e.g., "usuario" o "trabajador").
     */
    public String getRole() {
        return role;
    }

    /**
     * Establece el rol del usuario.
     * 
     * @param role El rol que se desea asignar al usuario (e.g., "usuario" o "trabajador").
     */
    public void setRole(String role) {
        this.role = role;
    }
}
