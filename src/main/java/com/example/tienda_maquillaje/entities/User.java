package com.example.tienda_maquillaje.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Clase que representa un usuario en la tienda de maquillaje.
 * 
 * Esta entidad se mapea a una tabla en la base de datos para almacenar
 * información sobre los usuarios registrados en el sistema.
 */
@Entity
public class User {

    /**
     * Identificador único del usuario.
     * Se genera automáticamente utilizando una estrategia de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de usuario.
     * Se utiliza para que el usuario inicie sesión en el sistema.
     */
    private String username;

    /**
     * Contraseña del usuario.
     * Es importante que en una implementación real, las contraseñas sean almacenadas
     * de forma segura utilizando técnicas de encriptación (e.g., BCrypt).
     */
    private String password;

    /**
     * Rol del usuario en el sistema.
     * Puede ser:
     * - "usuario": Representa a un cliente.
     * - "trabajador": Representa a un empleado o administrador del sistema.
     */
    private String role;

    // Métodos Getters y Setters

    /**
     * Obtiene el identificador único del usuario.
     * 
     * @return El identificador único del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     * 
     * @param id El identificador único a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     * 
     * @return El nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     * 
     * @param username El nombre de usuario a asignar.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password La contraseña a asignar.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el rol del usuario.
     * 
     * @return El rol del usuario.
     */
    public String getRole() {
        return role;
    }

    /**
     * Establece el rol del usuario.
     * 
     * @param role El rol a asignar (e.g., "usuario" o "trabajador").
     */
    public void setRole(String role) {
        this.role = role;
    }
}
