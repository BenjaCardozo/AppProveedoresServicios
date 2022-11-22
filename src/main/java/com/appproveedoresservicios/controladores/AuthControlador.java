package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.dto.request.AuthenticationRequest;
import com.appproveedoresservicios.dto.response.AuthenticationResponse;
import com.appproveedoresservicios.seguridad.JwtTokenProvider;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthControlador {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthenticationRequest authRequest) {

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getCorreo(), authRequest.getClave()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        //obtenemos el token del jwtTokenProvider
        String token = jwtTokenProvider.generarToken(auth.getName());
        
        System.out.println("TOKEN: "+token);

        return ResponseEntity.ok(new AuthenticationResponse(authRequest.getCorreo(), token));
    }
}
