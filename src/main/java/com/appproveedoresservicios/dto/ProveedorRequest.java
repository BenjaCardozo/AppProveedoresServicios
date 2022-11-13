package com.appproveedoresservicios.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProveedorRequest {
    
    @NotEmpty(message = "El nombre no puede estar vacío.")
    private String nombre;
    
    @NotEmpty(message = "El correo no puede estar vacío.")
    private String correo;
    
    @AssertTrue(message = "Las claves deben ser iguales.")
    private boolean clavesIguales(){
        return clave.equals(clave2);
    }
    
    @Min(value = 8,message = "La clave debe tener mínimo de 8 caracteres.")
    @Max(value = 16,message = "La clave debe tener máximo de 16 caracteres.")
    private String clave;
    private String clave2;
    
    @NotEmpty(message = "El barrio no puede estar vacío.")
    private String barrio;
    
    private MultipartFile foto;
    
    @NotEmpty(message = "El contacto no puede estar vacío.")
    private String contacto;
    
    @NotEmpty(message = "La descripción no puede estar vacía.")
    private String descripcion;
    
    @NotEmpty(message = "El rubro no puede estar vacío.")
    private String rubro;
    
    @NotEmpty(message = "La disponibilidad no puede estar vacía.")
    private String disponibilidad;
}
