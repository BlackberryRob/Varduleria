package com.verduleria.service;

import com.verduleria.domain.Producto;
import java.util.List;

public interface ProductoService {

    // Se obtiene un listado de productos en un List
    public List<Producto> getProductos(boolean activos);

    // Se obtiene un Producto, a partir del id de un producto
    public Producto getProducto(Producto producto);

    // Se inserta un nuevo producto si el id del producto esta vacío
    // Se actualiza un producto si el id del producto NO esta vacío
    public void save(Producto producto);

    // Se elimina el producto que tiene el id pasado por parámetro
    public void delete(Producto producto);


    /*Practica #4 Debe agregar al proyecto tienda una versión de una consulta ampliada de su libre escogencia, 
    puede ser de tabla producto, categoria o venta.*/
    List<Producto> findByDescripcionContainingIgnoreCaseOrderByPrecioAsc(String descripcion);

    //Ejemplo 2: Lista de productos utilizando consultas con JPQL    
    public List<Producto> metodoJPQL(double precioInf, double precioSup);

    //Ejemplo 3: Lista de productos utilizando consultas con SQL Nativo
    public List<Producto> metodoNativo(String descripcionCategoria);
}
