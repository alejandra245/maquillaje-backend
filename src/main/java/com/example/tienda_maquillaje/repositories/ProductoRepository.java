package com.example.tienda_maquillaje.repositories;

import com.example.tienda_maquillaje.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;  // Asegúrate de importar List

public interface ProductoRepository extends JpaRepository<Producto, Long> {
     // Método para buscar productos por nombre, ignorando mayúsculas/minúsculas
     List<Producto> findByNombreContainingIgnoreCase(String searchTerm);
}
