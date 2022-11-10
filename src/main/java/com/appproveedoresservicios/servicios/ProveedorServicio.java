
package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.entidades.Proveedor;



public interface ProveedorServicio {
    
    void crearProveedor ();
    
    void modificarProveedor();
    
    void eliminarProveedor();
    
    void darBajaProveedor();
    
    void darAltaProveedor();
    
    Proveedor buscarProveedor();
}
