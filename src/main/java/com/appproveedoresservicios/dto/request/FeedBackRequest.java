package com.appproveedoresservicios.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class FeedBackRequest {

    @NotEmpty(message = "La calificación no puede estar vacía.")
    private Integer calificacion;

    @Size(min = 0, max = 240, message = "El comentario supera el numero de caracteres posibles(240)")
    private String comentario;

    @NotEmpty(message = "El id de trabajo no es valido.")
    private Long idTrabajo;

}
