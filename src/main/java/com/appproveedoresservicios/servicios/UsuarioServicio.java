package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.entidades.Usuario;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;

public interface UsuarioServicio {
    
    //AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws InvalidCredentialsException;
    
    //void eliminarUsuario(Long id) throws Exception;
    
    //void darBajaUsuario(Long id) throws Exception;
    
    //void darAltaUsuario(Long id) throws Exception;
    
    Usuario findById(Long id) throws ResourceNotFoundException;
    
    boolean buscaPorCorreo(String correo);
}
