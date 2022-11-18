package com.appproveedoresservicios.dto;

import com.appproveedoresservicios.enums.Rol;
import lombok.Data;

@Data
public class AdministradorResponse {
    
    private Long id;
    private String nombre;
    private String correo;
    private Boolean alta;
    private Rol rol;
}
