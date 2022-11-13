package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.servicios.ProveedorServicioImp;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @GetMapping("/proveedor/{id}")
    public ResponseEntity<byte[]> fotoProveedor(@PathVariable Long id){
        try {
            Proveedor proveedor = proveedorServicioImp.findByID(id);
            if (proveedor.getFoto() == null) {
                throw new Exception("El proveedor no tiene una foto.");
            }
            
            byte[] foto = proveedor.getFoto().getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            
            return new ResponseEntity(foto, headers, HttpStatus.OK); 
        } catch (Exception ex) {
            Logger.getLogger(FotoControlador.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
