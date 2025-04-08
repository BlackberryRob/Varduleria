package com.verduleria.domain;

import lombok.Data;
import java.io.Serializable;
import jakarta.persistence.*;
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
    private int estado; //Falta implementarle sentido: Ej: 1=Pagado, 2=Pendiente, 3=Cancelado, etc...

    //Constructores
    public Factura() {
    }

    public Factura(long idUsuario) {
        this.idUsuario = idUsuario;
        this.fecha = Calendar.getInstance().getTime();
        this.estado = 1;
    }
    

}
