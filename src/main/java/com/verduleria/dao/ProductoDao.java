package com.verduleria.dao;

import com.verduleria.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ProductoDao extends JpaRepository<Producto, Long> {

    // Encuentra productos por descripción, ignorando mayúsculas y minúsculas
    List<Producto> findByDescripcionContainingIgnoreCaseOrderByPrecioAsc(String descripcion);

    //Ejemplo 2: de método utilizando Consultas con JPQL
    @Query(value = "SELECT a FROM Producto a where a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Producto> metodoJPQL(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);

    //Ejemplo 3: de método utilizando Consultas con SQL nativo
    @Query(nativeQuery = true,
            value = "SELECT p.* FROM producto p "
            + "JOIN categoria c ON p.id_categoria = c.id_categoria "
            + "WHERE LOWER(c.descripcion) = LOWER(:descripcionCategoria) "
            + "ORDER BY p.descripcion ASC")
    List<Producto> metodoNativo(@Param("descripcionCategoria") String descripcionCategoria);
}
