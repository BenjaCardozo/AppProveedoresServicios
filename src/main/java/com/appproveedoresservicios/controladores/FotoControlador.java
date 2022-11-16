package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.entidades.Administrador;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;
import com.appproveedoresservicios.servicios.AdministradorServicioImp;
import com.appproveedoresservicios.servicios.ProveedorServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foto")
public class FotoControlador {
    
    @Autowired
    ProveedorServicioImp proveedorServicioImp;
    
    @Autowired
    AdministradorServicioImp administradorServicioImp;
    
    @GetMapping("/proveedor/{id}")
    public ResponseEntity<byte[]> fotoProveedor(@PathVariable Long id){
        Proveedor proveedor = proveedorServicioImp.findById(id);

        if (proveedor.getFoto() == null) {
            throw new ResourceNotFoundException("El proveedor no tiene una foto.");
        }

        byte[] foto = proveedor.getFoto().getContenido();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity(foto, headers, HttpStatus.OK);
    }
    
    @GetMapping("/admin/{id}")
    public ResponseEntity<byte[]> fotoAdmin(@PathVariable Long id){
        
        Administrador admin = administradorServicioImp.findById(id);
            
        if (admin.getFoto() == null) {
            throw new ResourceNotFoundException("El administrador no tiene una foto.");
        }

        byte[] foto = admin.getFoto().getContenido();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity(foto, headers, HttpStatus.OK); 
    }
}
