package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.response.ListTrabajoResponse;
import com.appproveedoresservicios.dto.request.TrabajoRequest;
import com.appproveedoresservicios.dto.response.TrabajoResponse;
import com.appproveedoresservicios.entidades.Trabajo;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;

public interface TrabajoServicio {

    TrabajoResponse crearTrabajo(TrabajoRequest request);

    TrabajoResponse trabajoConFechaFinal(Long id);
    
    void eliminarTrabajo(Long id);

    void darAltaTrabajo(Long id) throws Exception;

    void darBajaTrabajo(Long id) throws Exception;

    Trabajo findById(Long id) throws ResourceNotFoundException;

    TrabajoResponse findTrabajoById(Long id) throws ResourceNotFoundException;

    ListTrabajoResponse listarTrabajos();
    
    ListTrabajoResponse listarTrabajoPorProveedor(Long idProveedor);
}
