package com.appproveedoresservicios.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticationRequest {
    
    @Email(message = "El correo tiene que tener un formato valido.")
    @NotBlank(message = "El correo no puede estar vacío o ser nulo.")
    private String correo;

    @NotBlank(message = "La contraseña con puede ser nula o estar vacía.")
    private String clave;
}
