package com.appproveedoresservicios.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    
    private Long id;
    private String rol;
    private String token;
}
