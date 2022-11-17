package com.appproveedoresservicios.dto;

import com.appproveedoresservicios.entidades.Cliente;
import com.appproveedoresservicios.entidades.Proveedor;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TrabajoResponse {
    
    private Long id;
    private Proveedor proveedor;
    private Cliente cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean alta;
    
}
