package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.dto.ProveedorRequest;
import com.appproveedoresservicios.dto.ProveedorResponse;
import com.appproveedoresservicios.servicios.ProveedorServicioImp;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proveedor")
public class ProveedorControlador {
    
    @Autowired
    ProveedorServicioImp proveedorServicioImp;
    
    @PostMapping ("/crear")
    public ResponseEntity<ProveedorResponse> crear (@RequestBody @Valid ProveedorRequest proveedorRequest) throws Exception{
        System.out.println(proveedorRequest.getNombre());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorServicioImp.crearProveedor(proveedorRequest));
    }
}
