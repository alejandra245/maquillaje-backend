package com.example.tienda_maquillaje.controllers;

import org.springframework.http.HttpStatus;
import com.example.tienda_maquillaje.entities.Producto;
import com.example.tienda_maquillaje.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import java.util.Map;

import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    

    @Autowired
    private ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Obtener todos los productos
    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    // Agregar un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@Valid @RequestBody Producto producto) {
        Producto nuevoProducto = productoRepository.save(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    // Actualizar un producto
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

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

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
    @PostMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarProducto(@RequestBody Map<String, String> request) {
        String searchTerm = request.get("searchTerm");  // Obtener el término de búsqueda
        List<Producto> productos = productoRepository.findByNombreContainingIgnoreCase(searchTerm); // Búsqueda en la base de datos
        return ResponseEntity.ok(productos);
    }
}