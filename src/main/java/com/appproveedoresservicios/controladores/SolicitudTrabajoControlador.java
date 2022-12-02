package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.dto.request.SolicitudTrabajoRequest;
import com.appproveedoresservicios.dto.response.ListSolicitudTrabajoResponse;
import com.appproveedoresservicios.dto.response.SolicitudTrabajoResponse;
import com.appproveedoresservicios.servicios.SolicitudTrabajoServicioImp;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solicitudtrabajo")
@CrossOrigin(origins = "*")
public class SolicitudTrabajoControlador {

    @Autowired
    SolicitudTrabajoServicioImp solicitudTrabajoServicioImp;

    @PostMapping
    @Transactional
    public ResponseEntity<SolicitudTrabajoResponse> crear(@Valid @RequestBody SolicitudTrabajoRequest solicitudTrabajoRequest){

        return ResponseEntity.status(HttpStatus.CREATED).body(solicitudTrabajoServicioImp.crearSolicitud(solicitudTrabajoRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) throws Exception {
        solicitudTrabajoServicioImp.eliminarTrabajoSolicitud(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudTrabajoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(solicitudTrabajoServicioImp.findSolicitudById(id));
    }

    @GetMapping
    public ResponseEntity<ListSolicitudTrabajoResponse> listar() {
        return ResponseEntity.ok().body(solicitudTrabajoServicioImp.listarSolicitudes());
    }

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<ListSolicitudTrabajoResponse> listarSolicitudesPorProveedor(@PathVariable Long id) {
        return ResponseEntity.ok().body(solicitudTrabajoServicioImp.buscarSolicitudesPorProveedor(id));
    }

}
