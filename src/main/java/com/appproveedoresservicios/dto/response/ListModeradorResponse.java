package com.appproveedoresservicios.dto.response;

import com.appproveedoresservicios.entidades.Moderador;
import java.util.List;
import lombok.Data;

@Data
public class ListModeradorResponse {
    
    List<Moderador> moderadores;
    
}
