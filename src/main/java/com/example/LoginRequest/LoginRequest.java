package com.example.tienda_maquillaje.LoginRequest;
/**
 * Clase que representa una solicitud de inicio de sesión.
 * 
 * Contiene los datos necesarios para que un usuario se autentique:
 * - Nombre de usuario (username).
 * - Contraseña (password).
 */
public class LoginRequest {
    // Atributo que almacena el nombre de usuario del cliente.
    private String username;

    // Atributo que almacena la contraseña del cliente.
    private String password;
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
     * Obtiene la contraseña.
     * 
     * @return La contraseña ingresada.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Establece la contraseña.
     * 
     * @param password La contraseña que se desea asignar.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
