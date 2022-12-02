package com.appproveedoresservicios.dto.response;

import lombok.Data;

@Data
public class SolicitudTrabajoResponse {
    private Long id;
    private Long idProveedor;
    private Long idCliente;
    private String solicitud;
    
    
}
