package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.dto.request.ModeradorRequest;
import com.appproveedoresservicios.dto.response.ModeradorResponse;
import com.appproveedoresservicios.excepciones.EmailAlreadyInUseException;
import com.appproveedoresservicios.servicios.ModeradorServicioImp;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/moderador")
public class ModeradorControlador {

    @Autowired
    ModeradorServicioImp moderadorServicioImp;

    @PostMapping
    @Transactional
    public ResponseEntity<ModeradorResponse> crear(@Valid @ModelAttribute ModeradorRequest moderadorRequest, BindingResult result) throws MethodArgumentNotValidException, EmailAlreadyInUseException {

        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(moderadorServicioImp.crearModerador(moderadorRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModeradorResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(moderadorServicioImp.findModeradorById(id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) throws Exception {
        moderadorServicioImp.eliminarModerador(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ModeradorResponse> actualizar(@PathVariable Long id,
            @Valid @ModelAttribute ModeradorRequest moderadorRequest, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }

        return ResponseEntity.ok().body(moderadorServicioImp.actualizarModerador(moderadorRequest, id));
    }

    @PatchMapping("baja/{id}")
    @Transactional
    public ResponseEntity<ModeradorResponse> darBaja(@PathVariable Long id) throws Exception {

        moderadorServicioImp.darBajaModerador(id);

        return ResponseEntity.ok().body(moderadorServicioImp.findModeradorById(id));
    }

    @PatchMapping("alta/{id}")
    @Transactional
    public ResponseEntity<ModeradorResponse> darAlta(@PathVariable Long id) throws Exception {

        moderadorServicioImp.darAltaModerador(id);

        return ResponseEntity.ok().body(moderadorServicioImp.findModeradorById(id));
    }

}
