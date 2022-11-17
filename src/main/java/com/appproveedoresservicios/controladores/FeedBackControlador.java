package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.dto.FeedBackRequest;
import com.appproveedoresservicios.dto.FeedBackResponse;
import com.appproveedoresservicios.dto.ListFeedBackResponse;
import com.appproveedoresservicios.servicios.FeedBackServicioImp;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feedback")
public class FeedBackControlador {

    @Autowired
    FeedBackServicioImp feedbackServicioImp;

    @PostMapping
    @Transactional
    public ResponseEntity<FeedBackResponse> crear(@Valid @ModelAttribute FeedBackRequest feedbackRequest, BindingResult result) throws MethodArgumentNotValidException {

        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackServicioImp.crearFeedBack(feedbackRequest));
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
