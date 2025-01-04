package com.example.tienda_maquillaje.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Clase que representa un producto en la tienda de maquillaje.
 * 
 * Esta entidad se mapea a una tabla en la base de datos y contiene
 * los atributos y validaciones necesarias para un producto.
 */
@Entity
public class Producto {

    /**
     * Identificador único del producto.
     * Se genera automáticamente usando una estrategia de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del producto.
     * Este campo es obligatorio y no puede estar en blanco.
     */
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    /**
     * Precio del producto.
     * Debe ser mayor que 0.
     */
    @Min(value = 1, message = "El precio debe ser mayor que 0")
    private double precio;

    /**
     * Cantidad disponible en el inventario.
     * No puede ser negativa.
     */
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private int cantidad;

    /**
     * URL de la imagen del producto.
     * Este campo es obligatorio y no puede estar en blanco.
     */
    @NotBlank(message = "La URL de la imagen es obligatoria")
    private String imagen;

    // Métodos Getters y Setters

    /**
     * Obtiene el identificador único del producto.
     * 
     * @return El identificador único.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del producto.
     * 
     * @param id El identificador único a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param nombre El nombre del producto a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return El precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * 
     * @param precio El precio del producto a asignar.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la cantidad disponible en inventario.
     * 
     * @return La cantidad disponible.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad disponible en inventario.
     * 
     * @param cantidad La cantidad a asignar.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene la URL de la imagen del producto.
     * 
     * @return La URL de la imagen.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Establece la URL de la imagen del producto.
     * 
     * @param imagen La URL de la imagen a asignar.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
