package com.verduleria.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verduleria.domain.Rol;

public interface RolDao extends JpaRepository<Rol, Long> {

}