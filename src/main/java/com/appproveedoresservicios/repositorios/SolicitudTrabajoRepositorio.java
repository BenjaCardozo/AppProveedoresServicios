package com.appproveedoresservicios.repositorios;

import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.entidades.SolicitudTrabajo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudTrabajoRepositorio extends JpaRepository<SolicitudTrabajo, Long> {

    List<SolicitudTrabajo> findByProveedor(Proveedor proveedor);

}
