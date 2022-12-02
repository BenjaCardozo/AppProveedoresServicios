package com.appproveedoresservicios.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FotoProveedorRequest {
    
    @NotNull(message = "Se necesita un proveedor.")
    private Long idProveedor;
    
    private MultipartFile foto;
}
