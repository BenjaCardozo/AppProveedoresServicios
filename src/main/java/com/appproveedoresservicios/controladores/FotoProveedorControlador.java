package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.dto.request.FotoProveedorRequest;
import com.appproveedoresservicios.dto.response.FotoProveedorResponse;
import com.appproveedoresservicios.dto.response.ListFotoProveedorResponse;
import com.appproveedoresservicios.servicios.FotoProveedorServicioImp;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fproveedor")
@CrossOrigin(origins = "*")
public class FotoProveedorControlador {

    @Autowired
    FotoProveedorServicioImp fotoProveedorServicioImp;

    @PostMapping
    @Transactional
    public ResponseEntity<FotoProveedorResponse> crear(@Valid @ModelAttribute FotoProveedorRequest request, BindingResult result) throws MethodArgumentNotValidException {
        
        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(fotoProveedorServicioImp.crearFotoProveedor(request));
    }
    
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<FotoProveedorResponse> actualizar(@Valid @ModelAttribute FotoProveedorRequest request,@PathVariable Long id, BindingResult result) throws MethodArgumentNotValidException {
        
        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(fotoProveedorServicioImp.actualizarFotoProveedor(request, id));
    }
    
    @GetMapping("/proveedor/{id}")
    public ResponseEntity<ListFotoProveedorResponse> listarTrabajoPorProveedor(@PathVariable Long id) {
        return ResponseEntity.ok().body(fotoProveedorServicioImp.listarFotosProveedor(id));
    }
}
