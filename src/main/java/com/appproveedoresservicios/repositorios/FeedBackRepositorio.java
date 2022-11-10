package com.appproveedoresservicios.repositorios;

import com.appproveedoresservicios.entidades.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepositorio extends JpaRepository<FeedBack, Long>{
    
}
