package com.example.tienda_maquillaje.service;

import com.example.tienda_maquillaje.entities.User;
import com.example.tienda_maquillaje.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // Método para registrar un usuario
    public boolean register(String username, String password, String role) {
        // Verificar si el usuario ya existe
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            return false; // Usuario ya existe
        }

        // Crear y guardar el nuevo usuario
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        userRepository.save(user);
        return true; // Registro exitoso
    }

    // Método para autenticar un usuario
    public Optional<User> authenticate(String username, String password) {
        // Buscar el usuario por nombre y verificar la contraseña
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password));
    }

    // Método para obtener el rol de un usuario
    public String getRoleByUsername(String username) {
        // Obtener el rol del usuario
        return userRepository.findByUsername(username)
                .map(User::getRole)
                .orElse(null);
    }
}
