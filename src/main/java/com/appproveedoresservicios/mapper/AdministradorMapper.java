package com.appproveedoresservicios.mapper;

import com.appproveedoresservicios.dto.AdministradorRequest;
import com.appproveedoresservicios.dto.AdministradorResponse;
import com.appproveedoresservicios.entidades.Administrador;
import com.appproveedoresservicios.entidades.Foto;
import com.appproveedoresservicios.enums.Rol;
import com.appproveedoresservicios.servicios.FotoServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdministradorMapper {
    
    @Autowired
    FotoServicioImp fotoServicioImp;
    
    public Administrador map(AdministradorRequest administradorRequest){
        
        Administrador administrador = new Administrador();
        
        //atributos de persona/administrador
        administrador.setNombre(administradorRequest.getNombre());
        administrador.setCorreo(administradorRequest.getCorreo());
        administrador.setClave(administradorRequest.getClave());
        Foto foto = fotoServicioImp.guardarFoto(administradorRequest.getFoto());
        administrador.setFoto(foto);
        administrador.setAlta(true);
        administrador.setRol(Rol.ADMIN);
        
        return administrador;
    }
    
    public AdministradorResponse map(Administrador administrador){
        
        AdministradorResponse response = new AdministradorResponse();
        
        response.setId(administrador.getId());
        response.setNombre(administrador.getNombre());
        response.setCorreo(administrador.getCorreo());
        response.setAlta(administrador.getAlta());
        response.setRol(administrador.getRol());
        
        return response;
    }
}
