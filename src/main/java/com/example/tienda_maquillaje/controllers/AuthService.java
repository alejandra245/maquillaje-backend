package com.example.tienda_maquillaje.service;

import com.example.tienda_maquillaje.entities.User;
import com.example.tienda_maquillaje.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio para la gestión de autenticación y usuarios.
 * 
 * Proporciona funcionalidades para registrar usuarios, autenticar credenciales y obtener roles.
 */
@Service
public class AuthService {

    // Inyección del repositorio de usuarios para interactuar con la base de datos.
    @Autowired
    private UserRepository userRepository;

    /**
     * Registra un nuevo usuario en el sistema.
     * 
     * @param username Nombre de usuario del nuevo usuario.
     * @param password Contraseña del nuevo usuario.
     * @param role Rol asignado al nuevo usuario.
     * @return {@code true} si el registro es exitoso, {@code false} si el usuario ya existe.
     */
    public boolean register(String username, String password, String role) {
        // Verificar si el usuario ya existe en la base de datos.
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            return false; // Usuario ya existe, no se puede registrar.
        }

        // Crear y guardar un nuevo usuario.
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // En una implementación real, asegúrate de encriptar las contraseñas.
        user.setRole(role);

        userRepository.save(user); // Guardar el usuario en la base de datos.
        return true; // Registro exitoso.
    }

    /**
     * Autentica un usuario con su nombre de usuario y contraseña.
     * 
     * @param username Nombre de usuario ingresado.
     * @param password Contraseña ingresada.
     * @return Un {@link Optional} con el usuario si la autenticación es exitosa,
     *         o vacío si las credenciales son inválidas.
     */
    public Optional<User> authenticate(String username, String password) {
        // Buscar el usuario por su nombre y validar la contraseña.
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password)); // Comparar contraseñas.
    }

    /**
     * Obtiene el rol de un usuario basado en su nombre de usuario.
     * 
     * @param username Nombre de usuario del que se desea obtener el rol.
     * @return El rol del usuario si existe, o {@code null} si el usuario no se encuentra.
     */
    public String getRoleByUsername(String username) {
        // Buscar el usuario y obtener su rol.
        return userRepository.findByUsername(username)
                .map(User::getRole) // Extraer el rol si el usuario existe.
                .orElse(null); // Devolver null si no existe el usuario.
    }
}
