package com.appproveedoresservicios.repositorios;

import com.appproveedoresservicios.entidades.FotoProveedor;
import com.appproveedoresservicios.entidades.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoProveedorRepositorio extends JpaRepository<FotoProveedor, Long>{
    
    List<FotoProveedor> findByProveedor(Proveedor proveedor);
}
