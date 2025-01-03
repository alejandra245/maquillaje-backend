package com.example.tienda_maquillaje.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    @Min(value = 1, message = "El precio debe ser mayor que 0")
    private double precio;

    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private int cantidad;

    @NotBlank(message = "La URL de la imagen es obligatoria") // Validación para la URL
    private String imagen;

    


    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
