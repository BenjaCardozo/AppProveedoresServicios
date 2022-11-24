
package com.appproveedoresservicios.mapper;

import com.appproveedoresservicios.dto.request.ModeradorRequest;
import com.appproveedoresservicios.dto.response.ModeradorResponse;
import com.appproveedoresservicios.entidades.Foto;
import com.appproveedoresservicios.entidades.Moderador;
import com.appproveedoresservicios.enums.Rol;
import com.appproveedoresservicios.servicios.FotoServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ModeradorMapper {
    
        
    @Autowired
    FotoServicioImp fotoServicioImp;
    
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    public Moderador map(ModeradorRequest moderadorRequest){
        
        Moderador moderador = new Moderador();
        
        //atributos de persona/administrador
        moderador.setNombre(moderadorRequest.getNombre());
        moderador.setCorreo(moderadorRequest.getCorreo());
        moderador.setClave(passwordEncoder.encode(moderadorRequest.getClave()));
        Foto foto = fotoServicioImp.guardarFoto(moderadorRequest.getFoto());
        moderador.setFoto(foto);
        moderador.setAlta(true);
        moderador.setRol(Rol.MODERADOR);
        
        return moderador;
    }
    
    public ModeradorResponse map(Moderador moderador){
        
        ModeradorResponse response = new ModeradorResponse();
        
        response.setId(moderador.getId());
        response.setNombre(moderador.getNombre());
        response.setCorreo(moderador.getCorreo());
        response.setAlta(moderador.getAlta());
        response.setRol(moderador.getRol());
        
        return response;
    }
}

   
