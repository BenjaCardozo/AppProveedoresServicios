
package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.ProveedorRequest;
import com.appproveedoresservicios.dto.ProveedorResponse;
import com.appproveedoresservicios.entidades.Proveedor;



public interface ProveedorServicio {
    
    ProveedorResponse crearProveedor (ProveedorRequest request) throws Exception ;
    
    ProveedorResponse modificarProveedor(ProveedorRequest request, Long id) throws Exception;
    
    Proveedor eliminarProveedor(Long id) throws Exception;
    
    void darBajaProveedor(Long id) throws Exception;
    
    void darAltaProveedor(Long id) throws Exception;
    
    Proveedor findByID(Long id) throws Exception;
}
