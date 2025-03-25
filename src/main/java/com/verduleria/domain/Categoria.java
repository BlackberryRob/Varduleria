package com.verduleria.domain;

import lombok.Data;
import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_categoria")
    private long idCategoria;
    private String descripcion;
    @Column(name = "ruta_imagen")
    private String rutaImagen;
    private boolean activo;

    //@OneToMany para unir tablas de muchos a uno. Si no hace fatla QUITARLO
    @OneToMany
    @JoinColumn(name = "id_categoria", updatable=false)
    List<Producto> productos;

    //Constructores
    public Categoria() {
    }

    public Categoria(String descripcion, boolean activo) {
        this.descripcion = descripcion;
        this.activo = activo;
    }

}
