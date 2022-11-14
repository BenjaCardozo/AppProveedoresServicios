
package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.ListProveedorResponse;
import com.appproveedoresservicios.dto.ProveedorRequest;
import com.appproveedoresservicios.dto.ProveedorResponse;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;



public interface ProveedorServicio {
    
    ProveedorResponse crearProveedor (ProveedorRequest request);
    
    ProveedorResponse modificarProveedor(ProveedorRequest request, Long id) throws Exception;
    
    Proveedor eliminarProveedor(Long id) throws Exception;
    
    void darBajaProveedor(Long id) throws Exception;
    
    void darAltaProveedor(Long id) throws Exception;
    
    Proveedor findById(Long id) throws ResourceNotFoundException;
    
    ProveedorResponse findProveedorById(Long id) throws ResourceNotFoundException;
    
    ListProveedorResponse buscarProveedorPorBarrio(String barrio) throws ResourceNotFoundException;
    
    ListProveedorResponse listarProveedores();
}
