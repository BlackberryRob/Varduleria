package com.verduleria.service.impl;

import com.verduleria.domain.Producto;
import com.verduleria.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.verduleria.dao.ProductoDao;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {
        var lista = productoDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }

    /*Practica #4 Debe agregar al proyecto tienda una versión de una consulta ampliada de su libre escogencia, 
    puede ser de tabla producto, categoria o venta.*/
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByDescripcionContainingIgnoreCaseOrderByPrecioAsc(String descripcion) {
        return productoDao.findByDescripcionContainingIgnoreCaseOrderByPrecioAsc(descripcion);
    }

    //Ejemplo 2
    @Override
    @Transactional(readOnly = true)
    public List<Producto> metodoJPQL(double precioInf, double precioSup) {
        return productoDao.metodoJPQL(precioInf, precioSup);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> metodoNativo(String descripcionCategoria) {
        return productoDao.metodoNativo(descripcionCategoria);
    }
}
