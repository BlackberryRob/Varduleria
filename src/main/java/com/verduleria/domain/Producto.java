package com.verduleria.domain;

import lombok.Data;
import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_producto")
    private long idProducto;
    private String descripcion;
    private String detalle;
    private double precio;
    private int existencias;
    @Column(name = "ruta_imagen")
    private String rutaImagen;
    private boolean activo;

    //@ManyToOne para unir tablas de muchos a uno. Si no hace fatla QUITARLO
    @ManyToOne​
    @JoinColumn(name = "id_categoria")
    Categoria categoria;

    //Constructores
    public Producto() {
    }

    public Producto(String descripcion, String detalle, double precio, int existencias, String imagen, boolean activo) {
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.precio = precio;
        this.existencias = existencias;
        this.rutaImagen = imagen;
        this.activo = activo;
    }

}
