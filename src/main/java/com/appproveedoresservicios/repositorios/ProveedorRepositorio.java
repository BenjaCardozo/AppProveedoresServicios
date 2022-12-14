package com.appproveedoresservicios.repositorios;

import com.appproveedoresservicios.entidades.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, Long> {

    List<Proveedor> findByBarrio(String barrio);

    List<Proveedor> findByRubro(String rubro);
    
    List<Proveedor> findByOrderByBarrio();

    List<Proveedor> findByOrderByBarrioDesc();

    List<Proveedor> findByOrderByRubro();

    List<Proveedor> findByOrderByRubroDesc();

    List<Proveedor> findByOrderByPromedioFeedback();
    
    List<Proveedor> findByOrderByPromedioFeedbackDesc();
    
    List<Proveedor> findByOrderByNombre();
    
    List<Proveedor> findByOrderByNombreDesc();
}
