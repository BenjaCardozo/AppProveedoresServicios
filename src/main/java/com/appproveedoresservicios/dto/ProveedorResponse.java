package com.appproveedoresservicios.dto;

import lombok.Data;

@Data
public class ProveedorResponse {
    private String nombre;
    private String correo;
    private String contacto;
    private String descripcion;
    private String rubro;
    private String disponibilidad;
}
