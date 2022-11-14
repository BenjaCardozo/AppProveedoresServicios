
package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.ListProveedorResponse;
import com.appproveedoresservicios.dto.ProveedorRequest;
import com.appproveedoresservicios.dto.ProveedorResponse;

import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;



public interface ProveedorServicio {
    
    ProveedorResponse crearProveedor (ProveedorRequest request);

    void modificarProveedor();
    
    void eliminarProveedor();
    
    void darBajaProveedor();
    
    void darAltaProveedor();
    

    Proveedor findById(Long id) throws ResourceNotFoundException;
    
    ProveedorResponse findProveedorById(Long id) throws ResourceNotFoundException;
    
    ListProveedorResponse listarProveedores();

}
