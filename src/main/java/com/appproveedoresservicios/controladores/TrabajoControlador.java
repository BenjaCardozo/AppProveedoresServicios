package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.dto.response.ListTrabajoResponse;
import com.appproveedoresservicios.dto.request.TrabajoRequest;
import com.appproveedoresservicios.dto.response.TrabajoResponse;
import com.appproveedoresservicios.servicios.TrabajoServicioImp;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trabajo")
public class TrabajoControlador {

    @Autowired
    TrabajoServicioImp trabajoServicioImp;

    @PostMapping
    @Transactional
    public ResponseEntity<TrabajoResponse> crear(@Valid @RequestBody TrabajoRequest trabajoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trabajoServicioImp.crearTrabajo(trabajoRequest));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TrabajoResponse> trabajoConFechaFinal(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(trabajoServicioImp.trabajoConFechaFinal(id));
    }

    @GetMapping
    public ResponseEntity<ListTrabajoResponse> listar() {
        return ResponseEntity.ok().body(trabajoServicioImp.listarTrabajos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrabajoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(trabajoServicioImp.findTrabajoById(id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) throws Exception {
        trabajoServicioImp.eliminarTrabajo(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("baja/{id}")
    @Transactional
    public ResponseEntity<TrabajoResponse> darBaja(@PathVariable Long id) throws Exception {

        trabajoServicioImp.darBajaTrabajo(id);

        return ResponseEntity.ok().body(trabajoServicioImp.findTrabajoById(id));
    }

    @PatchMapping("alta/{id}")
    @Transactional
    public ResponseEntity<TrabajoResponse> darAlta(@PathVariable Long id) throws Exception {

        trabajoServicioImp.darAltaTrabajo(id);

        return ResponseEntity.ok().body(trabajoServicioImp.findTrabajoById(id));
    }

}
