package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.dto.request.AuthenticationRequest;
import com.appproveedoresservicios.dto.response.AuthenticationResponse;
import com.appproveedoresservicios.seguridad.CustomUserDetailsService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthControlador {
    
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authRequest) {
        return ResponseEntity.ok(customUserDetailsService.login(authRequest));
    }
}
