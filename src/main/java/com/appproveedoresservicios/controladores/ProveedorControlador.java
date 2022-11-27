package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.dto.response.ListProveedorResponse;
import com.appproveedoresservicios.dto.request.ProveedorRequest;
import com.appproveedoresservicios.dto.response.ProveedorResponse;
import com.appproveedoresservicios.excepciones.EmailAlreadyInUseException;
import com.appproveedoresservicios.servicios.ProveedorServicioImp;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/proveedor")
@CrossOrigin(origins = "*")
public class ProveedorControlador {

    @Autowired
    ProveedorServicioImp proveedorServicioImp;

    @PostMapping
    @Transactional
    public ResponseEntity<ProveedorResponse> crear(@Valid @ModelAttribute ProveedorRequest proveedorRequest, BindingResult result) throws MethodArgumentNotValidException, EmailAlreadyInUseException {

        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorServicioImp.crearProveedor(proveedorRequest));
    }

    @GetMapping
    public ResponseEntity<ListProveedorResponse> listar() {
        return ResponseEntity.ok().body(proveedorServicioImp.listarProveedores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(proveedorServicioImp.findProveedorById(id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) throws Exception {
        proveedorServicioImp.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProveedorResponse> actualizar(@PathVariable Long id,
            @Valid @ModelAttribute ProveedorRequest proveedorRequest, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }

        return ResponseEntity.ok().body(proveedorServicioImp.modificarProveedor(proveedorRequest, id));
    }

    @PatchMapping("baja/{id}")
    @Transactional
    public ResponseEntity<ProveedorResponse> darBaja(@PathVariable Long id) throws Exception {

        proveedorServicioImp.darBajaProveedor(id);

        return ResponseEntity.ok().body(proveedorServicioImp.findProveedorById(id));
    }

    @PatchMapping("alta/{id}")
    @Transactional
    public ResponseEntity<ProveedorResponse> darAlta(@PathVariable Long id) throws Exception {

        proveedorServicioImp.darAltaProveedor(id);

        return ResponseEntity.ok().body(proveedorServicioImp.findProveedorById(id));
    }

    @PatchMapping("promedio/{id}")
    @Transactional
    public ResponseEntity<ProveedorResponse> actualizarPromedioFeedback(@PathVariable Long id) throws Exception {

        proveedorServicioImp.actualizarPromedioFeedBack(id);

        return ResponseEntity.ok().body(proveedorServicioImp.findProveedorById(id));
    }

    @GetMapping("/buscar/{barrio}")
    public ResponseEntity<ListProveedorResponse> listarPorBarrio(@PathVariable String barrio) {
        return ResponseEntity.ok().body(proveedorServicioImp.buscarProveedoresPorBarrio(barrio));
    }

    @GetMapping("/buscarRubro/{rubro}")
    public ResponseEntity<ListProveedorResponse> listarPorRubro(@PathVariable String rubro) {
        return ResponseEntity.ok().body(proveedorServicioImp.buscarProveedoresPorRubro(rubro));
    }

    @GetMapping("/barrios")
    public ResponseEntity<ListProveedorResponse> ordenarProveedoresPorBarrio() {
        return ResponseEntity.ok().body(proveedorServicioImp.ordenarProveedoresPorBarrios());
    }

    @GetMapping("/barriosDesc")
    public ResponseEntity<ListProveedorResponse> ordenarProveedoresPorBarrioDesc() {
        return ResponseEntity.ok().body(proveedorServicioImp.ordenarProveedoresPorBarriosDesc());
    }

    @GetMapping("/rubro")
    public ResponseEntity<ListProveedorResponse> ordenarProveedoresPorRubro() {
        return ResponseEntity.ok().body(proveedorServicioImp.ordenarProveedoresPorRubro());
    }

    @GetMapping("/rubroDesc")
    public ResponseEntity<ListProveedorResponse> ordenarProveedoresPorRubroDesc() {
        return ResponseEntity.ok().body(proveedorServicioImp.ordenarProveedoresPorRubroDesc());
    }

    @GetMapping("/feedbacks")
    public ResponseEntity<ListProveedorResponse> ordenarProveedoresPorFeedBack() {
        return ResponseEntity.ok().body(proveedorServicioImp.ordenarProveedoresPorFeedback());
    }

    @GetMapping("/feedbacksDesc")
    public ResponseEntity<ListProveedorResponse> ordenarProveedoresPorFeedBackDesc() {
        return ResponseEntity.ok().body(proveedorServicioImp.ordenarProveedoresPorFeedbackDesc());
    }

    @GetMapping("/nombres")
    public ResponseEntity<ListProveedorResponse> ordenarProveedoresPorNombre() {
        return ResponseEntity.ok().body(proveedorServicioImp.ordenarProveedoresPorNombre());
    }

    @GetMapping("/nombresDesc")
    public ResponseEntity<ListProveedorResponse> ordenarProveedoresPorNombreDesc() {
        return ResponseEntity.ok().body(proveedorServicioImp.ordenarProveedoresPorNombreDesc());
    }

}
