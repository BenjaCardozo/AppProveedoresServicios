package com.appproveedoresservicios.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProveedorRequest {
    private String nombre;
    private String correo;
    private String clave;
    private String clave2;
    private MultipartFile foto;
    private String contacto;
    private String descripcion;
    private String rubro;
    private String disponibilidad;
}
