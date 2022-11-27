package com.appproveedoresservicios.repositorios;

import com.appproveedoresservicios.entidades.Administrador;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepositorio extends JpaRepository<Administrador, Long> {
    
    List<Administrador> findByOrderByNombre();
    
    List<Administrador> findByOrderByNombreDesc();
    
}
