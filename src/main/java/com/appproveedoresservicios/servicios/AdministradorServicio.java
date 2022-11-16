package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.AdministradorRequest;
import com.appproveedoresservicios.dto.AdministradorResponse;
import com.appproveedoresservicios.entidades.Administrador;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;

public interface AdministradorServicio {
    
    AdministradorResponse crearAdmin(AdministradorRequest request);
    
    AdministradorResponse actualizarAdmin(AdministradorRequest request, Long id);
    
    void eliminarAdmin(Long id) throws Exception;
    
    void darBajaAdmin(Long id) throws Exception;
    
    void darAltaAdmin(Long id) throws Exception;
    
    Administrador findById(Long id) throws ResourceNotFoundException;
    
    AdministradorResponse findAdminById(Long id) throws ResourceNotFoundException;
}
