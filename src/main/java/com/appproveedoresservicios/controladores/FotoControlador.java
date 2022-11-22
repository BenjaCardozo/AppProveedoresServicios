package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.entidades.Administrador;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.entidades.Usuario;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;
import com.appproveedoresservicios.servicios.AdministradorServicioImp;
import com.appproveedoresservicios.servicios.ProveedorServicioImp;
import com.appproveedoresservicios.servicios.UsuarioServicioImp;
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
    /*
    @Autowired
    ProveedorServicioImp proveedorServicioImp;
    */
    @Autowired
    UsuarioServicioImp usuarioServicioImp;
    /*
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
    */
    @GetMapping("/usuario/{id}")
    public ResponseEntity<byte[]> fotoAdmin(@PathVariable Long id){
        
        Usuario usuario = usuarioServicioImp.findById(id);
            
        if (usuario.getFoto() == null) {
            throw new ResourceNotFoundException("El usuario no tiene una foto.");
        }

        byte[] foto = usuario.getFoto().getContenido();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity(foto, headers, HttpStatus.OK); 
    }
}
