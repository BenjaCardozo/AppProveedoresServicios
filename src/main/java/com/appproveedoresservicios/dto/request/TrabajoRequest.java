package com.appproveedoresservicios.dto.request;


import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TrabajoRequest {

    @NotEmpty(message = "Se necesita un proveedor.")
    private Long idProveedor;

    @NotEmpty(message = "Se necesita un cliente.")
    private Long idCliente;

}
