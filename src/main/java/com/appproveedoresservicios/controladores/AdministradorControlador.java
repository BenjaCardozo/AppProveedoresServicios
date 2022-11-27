package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.dto.request.AdministradorRequest;
import com.appproveedoresservicios.dto.response.AdministradorResponse;
import com.appproveedoresservicios.dto.response.ListAdministradorResponse;
import com.appproveedoresservicios.excepciones.EmailAlreadyInUseException;
import com.appproveedoresservicios.servicios.AdministradorServicioImp;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdministradorControlador {

    @Autowired
    AdministradorServicioImp administradorServicioImp;

    @PostMapping
    @Transactional
    public ResponseEntity<AdministradorResponse> crear(@Valid @ModelAttribute AdministradorRequest administradorRequest, BindingResult result) throws MethodArgumentNotValidException, EmailAlreadyInUseException {

        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(administradorServicioImp.crearAdmin(administradorRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(administradorServicioImp.findAdminById(id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) throws Exception {
        administradorServicioImp.eliminarAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AdministradorResponse> actualizar(@PathVariable Long id,
            @Valid @ModelAttribute AdministradorRequest administradorRequest, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }

        return ResponseEntity.ok().body(administradorServicioImp.actualizarAdmin(administradorRequest, id));
    }

    @PatchMapping("baja/{id}")
    @Transactional
    public ResponseEntity<AdministradorResponse> darBaja(@PathVariable Long id) throws Exception {

        administradorServicioImp.darBajaAdmin(id);

        return ResponseEntity.ok().body(administradorServicioImp.findAdminById(id));
    }

    @PatchMapping("alta/{id}")
    @Transactional
    public ResponseEntity<AdministradorResponse> darAlta(@PathVariable Long id) throws Exception {

        administradorServicioImp.darAltaAdmin(id);

        return ResponseEntity.ok().body(administradorServicioImp.findAdminById(id));
    }

    @GetMapping("/nombres")
    public ResponseEntity<ListAdministradorResponse> ordenarAdministradoresPorNombre() {
        return ResponseEntity.ok().body(administradorServicioImp.ordenarAdminsPorNombre());
    }

    @GetMapping("/nombresDesc")
    public ResponseEntity<ListAdministradorResponse> ordenarAdministradoresPorNombreDesc() {
        return ResponseEntity.ok().body(administradorServicioImp.ordenarAdminsPorNombreDesc());
    }

}
