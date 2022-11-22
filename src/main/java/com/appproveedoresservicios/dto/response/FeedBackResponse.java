package com.appproveedoresservicios.dto.response;

import lombok.Data;

@Data
public class FeedBackResponse {

    private Long id;
    private Integer calificacion;
    private String comentario;

}
