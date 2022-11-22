package com.appproveedoresservicios.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    
    private String correo;
    private String token;
}
