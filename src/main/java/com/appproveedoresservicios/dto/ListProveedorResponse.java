package com.appproveedoresservicios.dto;

import java.util.List;
import lombok.Data;

@Data
public class ListProveedorResponse {
    
    List<ProveedorResponse> proveedores;
}
