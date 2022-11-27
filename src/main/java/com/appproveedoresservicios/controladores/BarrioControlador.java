package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.dto.response.ListBarrioResponse;
import com.appproveedoresservicios.servicios.BarrioServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barrio")
public class BarrioControlador {
    
    @Autowired
    BarrioServicioImp barrioServicioImp;        
    
    @GetMapping
    public ResponseEntity<ListBarrioResponse> listar(){
        return ResponseEntity.ok().body(barrioServicioImp.listarBarrios());
    }        
    
}
