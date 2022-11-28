package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.request.AdministradorRequest;
import com.appproveedoresservicios.dto.response.AdministradorResponse;
import com.appproveedoresservicios.dto.response.ListAdministradorResponse;
import com.appproveedoresservicios.entidades.Administrador;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;
import com.appproveedoresservicios.excepciones.EmailAlreadyInUseException;

public interface AdministradorServicio {
    
    AdministradorResponse crearAdmin(AdministradorRequest request) throws EmailAlreadyInUseException;
    
    AdministradorResponse actualizarAdmin(AdministradorRequest request, Long id);
    
    void eliminarAdmin(Long id) throws Exception;
    
    void darBajaAdmin(Long id) throws Exception;
    
    void darAltaAdmin(Long id) throws Exception;
    
    Administrador findById(Long id) throws ResourceNotFoundException;
    
    AdministradorResponse findAdminById(Long id) throws ResourceNotFoundException;
    
    ListAdministradorResponse ordenarAdminsPorNombre();
    
    ListAdministradorResponse ordenarAdminsPorNombreDesc();    
}
