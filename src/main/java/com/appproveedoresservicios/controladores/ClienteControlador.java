package com.appproveedoresservicios.controladores;

import com.appproveedoresservicios.dto.request.ClienteRequest;
import com.appproveedoresservicios.dto.response.ClienteResponse;
import com.appproveedoresservicios.dto.response.ListClienteResponse;
import com.appproveedoresservicios.excepciones.EmailAlreadyInUseException;
import com.appproveedoresservicios.servicios.ClienteServicioImp;
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
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteControlador {

    @Autowired
    ClienteServicioImp clienteServicioImp;

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteResponse> crear(@Valid @ModelAttribute ClienteRequest clienteRequest, BindingResult result) throws MethodArgumentNotValidException, EmailAlreadyInUseException {

        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteServicioImp.crearCliente(clienteRequest));
    }

    @GetMapping
    public ResponseEntity<ListClienteResponse> listar() {
        return ResponseEntity.ok().body(clienteServicioImp.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteServicioImp.findClienteById(id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) throws Exception {
        clienteServicioImp.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClienteResponse> actualizar(@PathVariable Long id,
            @Valid @ModelAttribute ClienteRequest clienteRequest, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }

        return ResponseEntity.ok().body(clienteServicioImp.modificarCliente(clienteRequest, id));
    }

    @PatchMapping("baja/{id}")
    @Transactional
    public ResponseEntity<ClienteResponse> darBaja(@PathVariable Long id) throws Exception {

        clienteServicioImp.darBajaCliente(id);

        return ResponseEntity.ok().body(clienteServicioImp.findClienteById(id));
    }

    @PatchMapping("alta/{id}")
    @Transactional
    public ResponseEntity<ClienteResponse> darAlta(@PathVariable Long id) throws Exception {

        clienteServicioImp.darAltaCliente(id);

        return ResponseEntity.ok().body(clienteServicioImp.findClienteById(id));
    }

    @GetMapping("/buscar/{barrio}")
    public ResponseEntity<ListClienteResponse> listarPorBarrio(@PathVariable String barrio) {
        return ResponseEntity.ok().body(clienteServicioImp.buscarClientePorBarrio(barrio));
    }

    @GetMapping("/barrios")
    public ResponseEntity<ListClienteResponse> ordenarClientesPorBarrios() {
        return ResponseEntity.ok().body(clienteServicioImp.ordenarClientesPorBarrio());
    }

    @GetMapping("/barriosDesc")
    public ResponseEntity<ListClienteResponse> ordenarClientesPorBarriosDesc() {
        return ResponseEntity.ok().body(clienteServicioImp.ordenarClientesPorBarrioDesc());
    }

    @GetMapping("/nombres")
    public ResponseEntity<ListClienteResponse> ordenarClientesPorNombres() {
        return ResponseEntity.ok().body(clienteServicioImp.ordenarClientesPorNombres());
    }

    @GetMapping("/nombresDesc")
    public ResponseEntity<ListClienteResponse> ordenarClientesPorNombresDesc() {
        return ResponseEntity.ok().body(clienteServicioImp.ordenarClientesPorNombresDesc());
    }

}
