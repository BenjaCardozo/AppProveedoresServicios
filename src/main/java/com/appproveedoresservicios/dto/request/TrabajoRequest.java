package com.appproveedoresservicios.dto.request;


import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrabajoRequest {

    @NotNull(message = "Se necesita un proveedor.")
    private Long idProveedor;

    @NotNull(message = "Se necesita un cliente.")
    private Long idCliente;

}
