package com.appproveedoresservicios.dto.response;

import com.appproveedoresservicios.enums.Rol;
import lombok.Data;

@Data
public class ProveedorResponse {
    
    private Long id;
    private String nombre;
    private String correo;
    private String barrio;
    private Boolean alta;
    private Rol rol; 
    
    private String contacto;
    private String descripcion;
    private String rubro;
    private String disponibilidad;
}
