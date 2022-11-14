package com.appproveedoresservicios.mapper;

import com.appproveedoresservicios.dto.ProveedorRequest;
import com.appproveedoresservicios.dto.ProveedorResponse;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.enums.Rol;
<<<<<<< HEAD
=======
import com.appproveedoresservicios.servicios.FotoServicioImp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
>>>>>>> developer

public class ProveedorMapper {
    
<<<<<<< HEAD
=======
    @Autowired
    FotoServicioImp fotoServicioImp;
    
>>>>>>> developer
    public Proveedor map(ProveedorRequest proveedorRequest){
        
        Proveedor proveedor = new Proveedor();
        
        proveedor.setNombre(proveedorRequest.getNombre());
        proveedor.setCorreo(proveedorRequest.getCorreo());
        proveedor.setClave(proveedorRequest.getClave());
        proveedor.setBarrio(proveedorRequest.getBarrio());
<<<<<<< HEAD
        //LE PUSE NULL PORQUE FALTA HACER EL SERVICIO DE FOTO TODAVÍA
        proveedor.setFoto(null);
=======
        Foto foto = fotoServicioImp.guardarFoto(proveedorRequest.getFoto());
        proveedor.setFoto(foto);
>>>>>>> developer
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
    
    public List<ProveedorResponse> map (List<Proveedor> proveedores){
        
        List<ProveedorResponse> listResponse = new  ArrayList<>();
        
        for (Proveedor proveedor : proveedores) {
            listResponse.add(map(proveedor));
        }
        
        return listResponse;
    }
}
