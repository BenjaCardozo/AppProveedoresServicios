package com.appproveedoresservicios.mapper;


import com.appproveedoresservicios.dto.request.ProveedorRequest;
import com.appproveedoresservicios.dto.response.ProveedorResponse;
import com.appproveedoresservicios.entidades.Foto;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.enums.Rol;

import com.appproveedoresservicios.servicios.FotoServicioImp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

public class ProveedorMapper {

    @Autowired
    FotoServicioImp fotoServicioImp;
    
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    public Proveedor map(ProveedorRequest proveedorRequest){
        
        Proveedor proveedor = new Proveedor();
        
        proveedor.setNombre(proveedorRequest.getNombre());
        proveedor.setCorreo(proveedorRequest.getCorreo());
        proveedor.setClave(passwordEncoder.encode(proveedorRequest.getClave()));
        proveedor.setBarrio(proveedorRequest.getBarrio());

        Foto foto = fotoServicioImp.guardarFoto(proveedorRequest.getFoto());
        proveedor.setFoto(foto);
        proveedor.setAlta(true);
        proveedor.setRol(Rol.PROVEEDOR);
        
        proveedor.setContacto(proveedorRequest.getContacto());
        proveedor.setDescripcion(proveedorRequest.getDescripcion());
        proveedor.setRubro(proveedorRequest.getRubro());
        proveedor.setPromedioFeedback(0d);
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
        response.setPromedioFeedback(proveedor.getPromedioFeedback());
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
