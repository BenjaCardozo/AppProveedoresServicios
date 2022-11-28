package com.appproveedoresservicios.dto.response;

import com.appproveedoresservicios.enums.Rol;
import lombok.Data;

@Data
public class ModeradorResponse {
    
        
    private Long id;
    private String nombre;
    private String correo;
    private Boolean alta;
    private Rol rol;
}
