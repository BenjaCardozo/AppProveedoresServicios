package com.appproveedoresservicios.dto;

import lombok.Data;

@Data
public class FeedBackResponse {

    private Long id;
    private Integer calificacion;
    private String comentario;

}
