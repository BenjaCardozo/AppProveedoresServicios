package com.appproveedoresservicios.repositorios;

import com.appproveedoresservicios.entidades.Moderador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeradorRepositorio extends JpaRepository<Moderador, Long> {
    
}
