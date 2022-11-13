package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.dto.ProveedorResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proveedor")
public class ProveedorControlador {
    
    
    @PostMapping ("/crear")
    public void crear (ProveedorResponse proveedorResponse){
        
    }
}
