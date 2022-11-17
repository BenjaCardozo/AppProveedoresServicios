package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.ListTrabajoResponse;
import com.appproveedoresservicios.dto.TrabajoRequest;
import com.appproveedoresservicios.dto.TrabajoResponse;
import com.appproveedoresservicios.entidades.Trabajo;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;

public interface TrabajoServicio {

    TrabajoResponse crearTrabajo(TrabajoRequest request);

    void eliminarTrabajo(Long id);

    void darAltaTrabajo(Long id) throws Exception;

    void darBajaTrabajo(Long id) throws Exception;

    Trabajo findById(Long id) throws ResourceNotFoundException;

    TrabajoResponse findTrabajoById(Long id) throws ResourceNotFoundException;

    ListTrabajoResponse listarTrabajos();
}
