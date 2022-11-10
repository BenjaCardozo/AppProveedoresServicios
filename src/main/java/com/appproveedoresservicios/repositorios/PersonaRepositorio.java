package com.appproveedoresservicios.repositorios;

import com.appproveedoresservicios.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepositorio extends JpaRepository<Persona, String>{
    
}
