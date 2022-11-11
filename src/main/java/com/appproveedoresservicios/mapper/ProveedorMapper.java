package com.appproveedoresservicios.mapper;

import com.appproveedoresservicios.dto.ProveedorRequest;
import com.appproveedoresservicios.dto.ProveedorResponse;
import com.appproveedoresservicios.entidades.Proveedor;

public class ProveedorMapper {
    
    public Proveedor map(ProveedorRequest proveedorReqest){
        
        Proveedor proveedor = new Proveedor();
        
        proveedor.setNombre(proveedorReqest.getNombre());
        proveedor.setCorreo(proveedorReqest.getCorreo());
        
        return proveedor;
    }
    
    public ProveedorResponse map(Proveedor proveedor){
        
        ProveedorResponse response = new ProveedorResponse();
        
        return response;
    }
}
