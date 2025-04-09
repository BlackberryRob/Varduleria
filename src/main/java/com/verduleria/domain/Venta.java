package com.verduleria.domain;

import lombok.Data;
import java.io.Serializable;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Venta")
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_venta")
    private long idVenta;
    private long idFactura;
    private long idProducto;
    private double precio;
    private int cantidad;   

    //Constructores
    public Venta() {
    }

    public Venta(long idFactura, long idProducto, double precio, int cantidad) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    

}
