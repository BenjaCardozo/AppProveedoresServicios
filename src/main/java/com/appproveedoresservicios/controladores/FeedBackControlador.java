package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.dto.request.FeedBackRequest;
import com.appproveedoresservicios.dto.response.FeedBackResponse;
import com.appproveedoresservicios.dto.response.ListFeedBackResponse;
import com.appproveedoresservicios.servicios.FeedBackServicioImp;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
@CrossOrigin(origins = "*")
public class FeedBackControlador {

    @Autowired
    FeedBackServicioImp feedbackServicioImp;

    @PostMapping
    @Transactional
    public ResponseEntity<FeedBackResponse> crear(@Valid @RequestBody FeedBackRequest feedbackRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackServicioImp.crearFeedBack(feedbackRequest));

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<FeedBackResponse> actualizar(@Valid @RequestBody FeedBackRequest feedbackRequest,@PathVariable Long id) throws Exception {

        return ResponseEntity.ok().body(feedbackServicioImp.actualizarFeedBack(feedbackRequest, id));

    }

    @GetMapping
    public ResponseEntity<ListFeedBackResponse> listar() {
        return ResponseEntity.ok().body(feedbackServicioImp.listarFeedBacks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedBackResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(feedbackServicioImp.findFeedBackById(id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) throws Exception {
        feedbackServicioImp.eliminarFeedBack(id);
        return ResponseEntity.noContent().build();
    }
}
