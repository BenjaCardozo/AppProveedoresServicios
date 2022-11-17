package com.appproveedoresservicios.dto;

import com.appproveedoresservicios.entidades.Cliente;
import com.appproveedoresservicios.entidades.Proveedor;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TrabajoRequest {

    @NotEmpty(message = "Se necesita un proveedor.")
    private Proveedor proveedor;

    @NotEmpty(message = "Se necesita un cliente.")
    private Cliente cliente;

    @NotEmpty(message = "La fecha de inicio no puede estar vacia.")
    private LocalDate fechaInicio;

    @NotEmpty(message = "La fecha final no puede estar vacia.")
    private LocalDate fechaFin;

}
