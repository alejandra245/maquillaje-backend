package com.example.tienda_maquillaje.repositories;

import com.example.tienda_maquillaje.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio para la entidad {@link User}.
 * 
 * Proporciona métodos para interactuar con la base de datos relacionados con los usuarios,
 * utilizando las funcionalidades de Spring Data JPA.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por su nombre de usuario.
     * 
     * @param username El nombre de usuario que se desea buscar.
     * @return Un {@link Optional} que contiene el usuario si se encuentra, o vacío si no existe.
     */
    Optional<User> findByUsername(String username);
}
