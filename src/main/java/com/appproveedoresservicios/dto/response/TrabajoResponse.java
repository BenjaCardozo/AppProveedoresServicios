package com.appproveedoresservicios.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class TrabajoResponse {
    
    private Long id;
    private Long idProveedor;
    private Long idCliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long idFeedBack;
    private Boolean alta;
    
}
