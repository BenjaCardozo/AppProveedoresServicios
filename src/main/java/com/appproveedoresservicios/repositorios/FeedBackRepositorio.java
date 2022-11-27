package com.appproveedoresservicios.repositorios;

import com.appproveedoresservicios.entidades.FeedBack;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.entidades.Trabajo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepositorio extends JpaRepository<FeedBack, Long>{
    
    FeedBack findByTrabajo (Trabajo trabajo);
    
}
