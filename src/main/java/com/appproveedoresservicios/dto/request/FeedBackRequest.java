package com.appproveedoresservicios.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class FeedBackRequest {

    @NotNull(message = "La calificación no puede estar vacía.")
    private Integer calificacion;
    
    @Size(min = 0, max = 240, message = "El comentario supera el numero de caracteres posibles(240)")
    private String comentario;

    @NotNull(message = "El id de trabajo no es valido.")
    private Long idTrabajo;

}
