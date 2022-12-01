/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fotoproveedor")
@CrossOrigin(origins = "*")
public class FotoProveedorControlador {

    @Autowired
    FotoProveedorServicioImp fotoProveedorServicioImp;

    @PostMapping
    @Transactional
    public ResponseEntity<FotoProveedorResponse> crear(@Valid @RequestBody FotoProveedorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fotoProveedorServicioImp.crearFotoProveedor(request));
    }
    
    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<FotoProveedorResponse> actualizar(@Valid @RequestBody FotoProveedorRequest request, Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fotoProveedorServicioImp.actualizarFotoProveedor(request, id));
    }
    
    @GetMapping("/proveedor/{id}")
    public ResponseEntity<ListFotoProveedorResponse> listarTrabajoPorProveedor(@PathVariable Long id) {
        return ResponseEntity.ok().body(fotoProveedorServicioImp.listarFotosProveedor(id));
    }
}
