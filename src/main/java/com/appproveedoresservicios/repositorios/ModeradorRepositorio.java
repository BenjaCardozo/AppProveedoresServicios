package com.appproveedoresservicios.repositorios;

import com.appproveedoresservicios.entidades.Moderador;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeradorRepositorio extends JpaRepository<Moderador, Long> {

    List<Moderador> findByOrderByNombre();

    List<Moderador> findByOrderByNombreDesc();

}
