package com.example.tienda_maquillaje.controllers;

import org.springframework.http.HttpStatus;
import com.example.tienda_maquillaje.entities.Producto;
import com.example.tienda_maquillaje.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.Optional;
import java.util.List;

/**
 * Controlador para la gestión de productos.
 * 
 * Proporciona endpoints para operaciones CRUD y funcionalidades adicionales
 * relacionadas con los productos.
 */
@RestController
@RequestMapping("/productos")
public class ProductoController {

    // Inyección del repositorio de productos.
    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Constructor del controlador.
     * 
     * @param productoRepository Repositorio de productos para interactuar con la base de datos.
     */
    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Obtener todos los productos.
     * 
     * @return Lista de todos los productos en la base de datos.
     */
    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    /**
     * Agregar un nuevo producto.
     * 
     * @param producto Producto que se desea agregar, validado automáticamente.
     * @return El producto agregado con un código HTTP 200 OK.
     */
    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@Valid @RequestBody Producto producto) {
        Producto nuevoProducto = productoRepository.save(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    /**
     * Actualizar un producto existente.
     * 
     * @param id Identificador del producto que se desea actualizar.
     * @param productoActualizado Datos actualizados del producto, validados automáticamente.
     * @return El producto actualizado con un código HTTP 200 OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody Producto productoActualizado) {
        Producto producto = productoRepository.findById(id)
            .map(prod -> {
                prod.setNombre(productoActualizado.getNombre());
                prod.setPrecio(productoActualizado.getPrecio());
                prod.setCantidad(productoActualizado.getCantidad());
                prod.setImagen(productoActualizado.getImagen());
                return productoRepository.save(prod);
            })
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return ResponseEntity.ok(producto);
    }

    /**
     * Eliminar un producto por ID.
     * 
     * @param id Identificador del producto a eliminar.
     * @return Código HTTP 204 No Content si la eliminación es exitosa, o 404 Not Found si no se encuentra el producto.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    /**
     * Comprar un producto.
     * 
     * Reduce en 1 la cantidad disponible del producto si hay stock.
     * 
     * @param id Identificador del producto a comprar.
     * @return Mensaje de éxito o error dependiendo de la disponibilidad del producto.
     */
    @PostMapping("/comprar/{id}")
    public ResponseEntity<?> comprarProducto(@PathVariable Long id) {
        Optional<Producto> productoOpt = productoRepository.findById(id);

        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            if (producto.getCantidad() > 0) {
                // Disminuye el stock del producto
                producto.setCantidad(producto.getCantidad() - 1);
                productoRepository.save(producto); // Guarda los cambios en la base de datos
                return ResponseEntity.ok("Compra exitosa.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Producto no disponible.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado.");
        }
    }

    /**
     * Buscar productos por nombre.
     * 
     * Busca productos cuyo nombre contenga el término de búsqueda (sin sensibilidad a mayúsculas/minúsculas).
     * 
     * @param request Mapa que contiene el término de búsqueda bajo la clave "searchTerm".
     * @return Lista de productos que coinciden con el término de búsqueda.
     */
    @PostMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarProducto(@RequestBody Map<String, String> request) {
        String searchTerm = request.get("searchTerm"); // Obtener el término de búsqueda
        List<Producto> productos = productoRepository.findByNombreContainingIgnoreCase(searchTerm); // Búsqueda en la base de datos
        return ResponseEntity.ok(productos);
    }
}
