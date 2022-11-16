package com.appproveedoresservicios.repositorios;

import com.appproveedoresservicios.entidades.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    List<Cliente> findByBarrio(String barrio);

}
