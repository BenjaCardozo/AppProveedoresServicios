package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.ProveedorRequest;
import com.appproveedoresservicios.dto.ProveedorResponse;
import com.appproveedoresservicios.entidades.Foto;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.excepciones.AppExcepcion;
import com.appproveedoresservicios.mapper.ProveedorMapper;
import com.appproveedoresservicios.repositorios.ProveedorRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorServicioImp implements ProveedorServicio {

    @Autowired
    ProveedorRepositorio proveedorRepositorio;

    @Autowired
    ProveedorMapper mapper;

    @Autowired
    FotoServicio fotoServicio;

    @Override
    public ProveedorResponse crearProveedor(ProveedorRequest request) throws Exception {

        validar(request);

        Proveedor proveedor = mapper.map(request);

        proveedorRepositorio.save(proveedor);

        return mapper.map(proveedor);
    }

    @Override
    public ProveedorResponse modificarProveedor(ProveedorRequest request, Long id) throws Exception {

        validar(request);

        Optional<Proveedor> respuesta = proveedorRepositorio.findById(id);
        
        if (respuesta.isPresent()) {

            Proveedor proveedor = respuesta.get();

            proveedor.setNombre(request.getNombre());
            proveedor.setCorreo(request.getCorreo());
            proveedor.setClave(request.getClave());
            proveedor.setContacto(request.getContacto());
            proveedor.setDisponibilidad(request.getDisponibilidad());
            proveedor.setBarrio(request.getBarrio());

            Long fotoId = null;
            if (request.getFoto() != null) {
                fotoId = proveedor.getFoto().getId();
            }

            Foto foto = fotoServicio.actualizarFoto(request.getFoto(), fotoId);

            proveedor.setFoto(foto);

            proveedorRepositorio.save(proveedor);

            return mapper.map(proveedor);
        }
        
        return null;

    }

    @Override
    public Proveedor findByID(Long id) throws Exception {
        Optional<Proveedor> proveedor = proveedorRepositorio.findById(id);
        if (proveedor.isPresent()) {
            return proveedor.get();
        } else {
            throw new Exception("Este proveedor no existe");
        }
    }

    @Override
    public Proveedor eliminarProveedor(Long id) throws Exception {
        findByID(id);
        proveedorRepositorio.deleteById(id);
        return null;
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
    
    private void validar(ProveedorRequest request) throws AppExcepcion {

        if (request.getCorreo().isEmpty() || request.getCorreo() == null) {
            throw new AppExcepcion("El correo no puede estar vacío.");
        }

        if (request.getNombre().isEmpty() || request.getNombre() == null) {
            throw new AppExcepcion("El nombre no puede estar vacío.");
        }

        if (request.getClave().length() < 8 || request.getClave().length() > 16) {
            throw new AppExcepcion("La clave tiene que ser de un mínimo de 8 caracteres y un máximo de 16 caracteres.");
        }

        if (!request.getClave().equals(request.getClave2())) {
            throw new AppExcepcion("Las claves deben ser iguales.");
        }

        if (request.getBarrio().isEmpty() || request.getBarrio() == null) {
            throw new AppExcepcion("El barrio no puede estar vacío.");
        }

        if (request.getContacto().isEmpty() || request.getContacto() == null) {
            throw new AppExcepcion("El contacto no puede estar vacío.");
        }

        if (request.getDescripcion().isEmpty() || request.getDescripcion() == null) {
            throw new AppExcepcion("El contacto no puede estar vacío.");
        }

        if (request.getRubro().isEmpty() || request.getRubro() == null) {
            throw new AppExcepcion("El rubro no puede estar vacio");
        }

        if (request.getDisponibilidad().isEmpty() || request.getDisponibilidad() == null) {
            throw new AppExcepcion("El rubro no puede estar vacio");
        }

    }

}
