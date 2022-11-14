package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.ListProveedorResponse;
import com.appproveedoresservicios.dto.ProveedorRequest;
import com.appproveedoresservicios.dto.ProveedorResponse;
import com.appproveedoresservicios.entidades.Foto;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.excepciones.DataNotFoundException;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;
import com.appproveedoresservicios.mapper.ProveedorMapper;
import com.appproveedoresservicios.repositorios.ProveedorRepositorio;
import java.util.List;
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
    FotoServicioImp fotoServicioImp;

    @Override
    public ProveedorResponse crearProveedor(ProveedorRequest request) {

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
            proveedor.setClave(request.getClave());
            proveedor.setContacto(request.getContacto());
            proveedor.setDisponibilidad(request.getDisponibilidad());
            proveedor.setBarrio(request.getBarrio());

            Long fotoId = null;
            if (request.getFoto() != null) {
                fotoId = mapper.map(request).getFoto().getId();
            }

            Foto foto = fotoServicioImp.actualizarFoto(request.getFoto(), fotoId);

            proveedor.setFoto(foto);

            proveedorRepositorio.save(proveedor);
            if (fotoId != null) {
                fotoServicioImp.eliminarFoto(fotoId);
            }
        }

        return mapper.map(proveedor);
    }

    @Override
    public Proveedor eliminarProveedor(Long id) throws Exception {
        findById(id);
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

    @Override
    public Proveedor findById(Long id) throws ResourceNotFoundException {
        Optional<Proveedor> proveedor = proveedorRepositorio.findById(id);
        if (proveedor.isPresent()) {
            return proveedor.get();
        } else {
            throw new ResourceNotFoundException("Este proveedor no existe");
        }
    }

    @Override
    public ProveedorResponse findProveedorById(Long id) throws ResourceNotFoundException {
        return mapper.map(findById(id));
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
}
