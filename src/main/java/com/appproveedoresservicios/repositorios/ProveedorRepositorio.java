package com.appproveedoresservicios.repositorios;

import com.appproveedoresservicios.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, Long>{
    
}
