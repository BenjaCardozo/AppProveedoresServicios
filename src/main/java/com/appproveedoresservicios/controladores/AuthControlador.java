package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.dto.AuthenticationRequest;
import com.appproveedoresservicios.dto.AuthenticationResponse;
import com.appproveedoresservicios.excepciones.InvalidCredentialsException;
import com.appproveedoresservicios.servicios.UsuarioServicioImp;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthControlador {
    
    @Autowired
    UsuarioServicioImp usuarioServicioImp;
    
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
        @RequestBody @Valid AuthenticationRequest authRequest) throws
        InvalidCredentialsException {
        
        return ResponseEntity.ok(usuarioServicioImp.login(authRequest));
    }
}
