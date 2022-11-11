package com.appproveedoresservicios.mapper;

import com.appproveedoresservicios.dto.ProveedorRequest;
import com.appproveedoresservicios.dto.ProveedorResponse;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.enums.Rol;

public class ProveedorMapper {
    
    public Proveedor map(ProveedorRequest proveedorRequest){
        
        Proveedor proveedor = new Proveedor();
        
        proveedor.setNombre(proveedorRequest.getNombre());
        proveedor.setCorreo(proveedorRequest.getCorreo());
        proveedor.setClave(proveedorRequest.getClave());
        proveedor.setBarrio(proveedorRequest.getBarrio());
        //LE PUSE NULL PORQUE FALTA HACER EL SERVICIO DE FOTO TODAVÍA
        proveedor.setFoto(null);
        proveedor.setAlta(true);
        proveedor.setRol(Rol.PROVEEDOR);
        
        proveedor.setContacto(proveedorRequest.getContacto());
        proveedor.setDescripcion(proveedorRequest.getDescripcion());
        proveedor.setRubro(proveedorRequest.getRubro());
        proveedor.setDisponibilidad(proveedorRequest.getDisponibilidad());
        
        return proveedor;
    }
    
    public ProveedorResponse map(Proveedor proveedor){
        
        ProveedorResponse response = new ProveedorResponse();
        
        response.setNombre(proveedor.getNombre());
        response.setCorreo(proveedor.getCorreo());
        response.setBarrio(proveedor.getBarrio());
        response.setAlta(proveedor.getAlta());
        response.setRol(proveedor.getRol());
        
        response.setContacto(proveedor.getContacto());
        response.setDescripcion(proveedor.getDescripcion());
        response.setRubro(proveedor.getRubro());
        response.setDisponibilidad(proveedor.getDisponibilidad());
        
        return response;
    }
}
