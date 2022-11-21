package com.appproveedoresservicios.dto.response;

import com.appproveedoresservicios.enums.Rol;
import lombok.Data;

@Data
public class ClienteResponse {
    
    private Long id;
    private String nombre;
    private String correo;
    private String barrio;
    private Boolean alta;
    private Rol rol; 
    private String contacto;
}
