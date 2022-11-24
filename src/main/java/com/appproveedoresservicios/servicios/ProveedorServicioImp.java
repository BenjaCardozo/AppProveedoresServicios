package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.response.ListProveedorResponse;
import com.appproveedoresservicios.dto.request.ProveedorRequest;
import com.appproveedoresservicios.dto.response.ProveedorResponse;
import com.appproveedoresservicios.entidades.Foto;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.entidades.Trabajo;
import com.appproveedoresservicios.excepciones.DataNotFoundException;
import com.appproveedoresservicios.excepciones.EmailAlreadyInUseException;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;
import com.appproveedoresservicios.mapper.ProveedorMapper;
import com.appproveedoresservicios.repositorios.ProveedorRepositorio;
import com.appproveedoresservicios.repositorios.TrabajoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProveedorServicioImp implements ProveedorServicio {

    @Autowired
    ProveedorRepositorio proveedorRepositorio;

    @Autowired
    TrabajoRepositorio trabajoRepositorio;

    @Autowired
    ProveedorMapper mapper;

    @Autowired
    FotoServicioImp fotoServicioImp;

    @Autowired
    UsuarioServicioImp usuarioServicioImp;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public ProveedorResponse crearProveedor(ProveedorRequest request) throws EmailAlreadyInUseException {

        if (usuarioServicioImp.buscaPorCorreo(request.getCorreo())) {
            throw new EmailAlreadyInUseException("Ese correo ya está en uso, ingresa otro.");
        }

        Proveedor proveedor = mapper.map(request);

        proveedorRepositorio.save(proveedor);

        return mapper.map(proveedor);
    }

    @Override
    public ProveedorResponse modificarProveedor(ProveedorRequest request, Long id) throws Exception {

        Optional<Proveedor> respuesta = proveedorRepositorio.findById(id);
        Proveedor proveedor = null;

        if (respuesta.isPresent()) {

            proveedor = respuesta.get();

            proveedor.setNombre(request.getNombre());
            proveedor.setCorreo(request.getCorreo());
            proveedor.setClave(passwordEncoder.encode(request.getClave()));
            proveedor.setContacto(request.getContacto());
            proveedor.setDisponibilidad(request.getDisponibilidad());
            proveedor.setBarrio(request.getBarrio());

            Long fotoId = null;
            if (proveedor.getFoto() != null) {
                fotoId = proveedor.getFoto().getId();
            }

            Foto foto = fotoServicioImp.actualizarFoto(request.getFoto(), fotoId);
            proveedor.setFoto(foto);

            proveedorRepositorio.save(proveedor);
        }

        return mapper.map(proveedor);
    }

    @Override
    public void actualizarPromedioFeedBack(Long id) throws Exception {

        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }

        Optional<Proveedor> respuesta = proveedorRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Proveedor proveedor = respuesta.get();
            proveedor.setPromedioFeedback(calcularFeedbackPromedio(id));
            
            proveedorRepositorio.save(proveedor);
        }
    }

    @Override
    public void eliminarProveedor(Long id) throws Exception {

        findById(id);
        fotoServicioImp.eliminarFoto(findById(id).getFoto().getId());
        proveedorRepositorio.deleteById(id);
    }

    @Override
    public void darAltaProveedor(Long id) throws Exception {

        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }

        Optional<Proveedor> respuesta = proveedorRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Proveedor proveedor = respuesta.get();
            proveedor.setAlta(Boolean.TRUE);

            proveedorRepositorio.save(proveedor);
        }
    }

    @Override
    public void darBajaProveedor(Long id) throws Exception {

        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }

        Optional<Proveedor> respuesta = proveedorRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Proveedor proveedor = respuesta.get();
            proveedor.setAlta(Boolean.FALSE);

            proveedorRepositorio.save(proveedor);
        }
    }

    @Override
    public Proveedor findById(Long id) throws ResourceNotFoundException {

        Optional<Proveedor> proveedor = proveedorRepositorio.findById(id);

        if (proveedor.isPresent()) {
            return proveedor.get();
        } else {
            throw new ResourceNotFoundException("Ese proveedor no existe");
        }
    }

    @Override
    public ProveedorResponse findProveedorById(Long id) throws ResourceNotFoundException {
        return mapper.map((Proveedor) usuarioServicioImp.findById(id));
    }

    @Override
    public ListProveedorResponse buscarProveedorPorBarrio(String barrio) throws ResourceNotFoundException {

        List<Proveedor> proveedores = proveedorRepositorio.findByBarrio(barrio);

        if (proveedores.size() < 1) {
            throw new DataNotFoundException("No hay proveedores en la base de datos, agrega algunos.");
        }
        ListProveedorResponse proveedoresResponse = new ListProveedorResponse();

        proveedoresResponse.setProveedores(mapper.map(proveedores));

        return proveedoresResponse;
    }

    @Override
    public ListProveedorResponse listarProveedores() {

        List<Proveedor> proveedores = proveedorRepositorio.findAll();

        if (proveedores.size() < 1) {
            throw new DataNotFoundException("No hay proveedores en la base de datos, agrega algunos.");
        }

        ListProveedorResponse proveedoresResponse = new ListProveedorResponse();

        proveedoresResponse.setProveedores(mapper.map(proveedores));

        return proveedoresResponse;
    }

    @Override
    public double calcularFeedbackPromedio(Long id) {

        Proveedor proveedor = findById(id);

        List<Trabajo> trabajos = trabajoRepositorio.findByProveedor(proveedor);

        if (trabajos.size() < 1) {
            throw new DataNotFoundException("Este proveedor no posee ningun trabajo, agrega algunos.");
        }

        double suma = 0;

        for (Trabajo trabajo : trabajos) {
            suma = suma + trabajo.getFeedback().getCalificacion();
        }

        return suma / trabajos.size();
    }
}
