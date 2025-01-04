package com.example.tienda_maquillaje.repositories;

import com.example.tienda_maquillaje.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio para la entidad {@link Producto}.
 * 
 * Proporciona métodos para interactuar con la base de datos relacionados con los productos,
 * aprovechando las funcionalidades de Spring Data JPA.
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Busca productos cuyo nombre contenga el término especificado, 
     * ignorando mayúsculas y minúsculas.
     * 
     * @param searchTerm El término de búsqueda.
     * @return Una lista de productos que coinciden con el término de búsqueda.
     */
    List<Producto> findByNombreContainingIgnoreCase(String searchTerm);
}
