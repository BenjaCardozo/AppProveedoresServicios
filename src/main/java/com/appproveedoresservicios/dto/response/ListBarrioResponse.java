package com.appproveedoresservicios.dto.response;

import com.appproveedoresservicios.enums.Barrio;
import java.util.List;
import lombok.Data;

@Data
public class ListBarrioResponse {
    
    List<Barrio> barrios;
    
}
