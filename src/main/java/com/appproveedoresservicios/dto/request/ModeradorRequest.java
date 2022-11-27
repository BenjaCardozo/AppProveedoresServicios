package com.appproveedoresservicios.dto.request;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ModeradorRequest {

    @NotEmpty(message = "El campo 'nombre' no puede estar vacío.")
    private String nombre;

    @NotEmpty(message = "El campo 'correo' no puede estar vacío.")
    @Email(message = "Debe ingresar un correo valido.")
    private String correo;

    @AssertTrue(message = "Las claves deben ser iguales.")
    private boolean isClavesIguales() {
        return clave.equals(clave2);
    }

    @Size(min = 8, max = 16, message = "La contraseña debe tener entre 8 y 16 caracteres.")
    private String clave;

    @Size(min = 8, max = 16, message = "La contraseña debe tener entre 8 y 16 caracteres.")
    private String clave2;

    private MultipartFile foto;

}
