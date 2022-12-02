package com.appproveedoresservicios.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SolicitudTrabajoRequest {

    @NotNull(message = "Se necesita un proveedor.")
    private Long idProveedor;

    @NotNull(message = "Se necesita un cliente.")
    private Long idCliente;

    @NotEmpty(message = "El campo 'solicitud' no puede estar vacío.")
    private String solicitud;

}
