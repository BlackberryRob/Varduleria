package com.verduleria.dao;

import com.verduleria.domain.Factura;
import org.springframework.data.jpa.repository.*;

public interface FacturaDao extends JpaRepository<Factura, Long> {

 }
