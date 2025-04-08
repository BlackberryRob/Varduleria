/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.verduleria.domain;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;

@Data
@Entity
@Table(name = "Factura")
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private long idFactura;
    private long idUsuario;
    private Date fecha;
    private double total;
    private int estado;

 
        @ManyToOne
    @JoinColumn(name = "id_categoria")
    Categoria categoria;


    public Factura() {
    }
        public Factura(long idUsuario) {
        this.idUsuario = idUsuario;
        this.fecha = Calendar.getInstance().getTime();
        this.estado = 1;
    }


}
