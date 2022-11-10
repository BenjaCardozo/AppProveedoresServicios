package com.appproveedoresservicios.repositorios;

import com.appproveedoresservicios.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepositorio extends JpaRepository<Proveedor, Long> {
    
    
}
