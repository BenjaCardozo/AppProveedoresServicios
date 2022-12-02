package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.request.SolicitudTrabajoRequest;
import com.appproveedoresservicios.dto.response.ListSolicitudTrabajoResponse;
import com.appproveedoresservicios.dto.response.SolicitudTrabajoResponse;
import com.appproveedoresservicios.entidades.SolicitudTrabajo;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;

public interface SolicitudTrabajoServicio {
    
    SolicitudTrabajoResponse crearSolicitud(SolicitudTrabajoRequest request);
    
    SolicitudTrabajo findById(Long id) throws ResourceNotFoundException;
    
    SolicitudTrabajoResponse findSolicitudById(Long id) throws ResourceNotFoundException;
    
    void eliminarTrabajoSolicitud(Long id) throws Exception;
    
    ListSolicitudTrabajoResponse listarSolicitudes();
    
    ListSolicitudTrabajoResponse buscarSolicitudesPorProveedor(Long idProveedor) throws ResourceNotFoundException;
}
