package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.request.AdministradorRequest;
import com.appproveedoresservicios.dto.response.AdministradorResponse;
import com.appproveedoresservicios.dto.response.ListAdministradorResponse;
import com.appproveedoresservicios.entidades.Administrador;
import com.appproveedoresservicios.entidades.Foto;
import com.appproveedoresservicios.excepciones.DataNotFoundException;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;
import com.appproveedoresservicios.excepciones.EmailAlreadyInUseException;
import com.appproveedoresservicios.mapper.AdministradorMapper;
import com.appproveedoresservicios.repositorios.AdministradorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServicioImp implements AdministradorServicio {

    @Autowired
    AdministradorRepositorio administradorRepositorio;

    @Autowired
    AdministradorMapper mapper;

    @Autowired
    FotoServicioImp fotoServicioImp;

    @Autowired
    UsuarioServicioImp usuarioServicioImp;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public AdministradorResponse crearAdmin(AdministradorRequest request) throws EmailAlreadyInUseException {

        if (usuarioServicioImp.buscaPorCorreo(request.getCorreo())) {
            throw new EmailAlreadyInUseException("Ese correo ya está en uso, ingresa otro.");
        }

        Administrador admin = mapper.map(request);

        administradorRepositorio.save(admin);

        return mapper.map(admin);
    }

    @Override
    public AdministradorResponse actualizarAdmin(AdministradorRequest request, Long id) {

        Optional<Administrador> respuesta = administradorRepositorio.findById(id);
        Administrador admin = null;

        if (respuesta.isPresent()) {

            admin = respuesta.get();

            admin.setNombre(request.getNombre());
            admin.setCorreo(request.getCorreo());
            admin.setClave(passwordEncoder.encode(request.getClave()));

            Long fotoId = null;
            if (admin.getFoto() != null) {
                fotoId = admin.getFoto().getId();
            }

            Foto foto = fotoServicioImp.actualizarFoto(request.getFoto(), fotoId);
            admin.setFoto(foto);

            administradorRepositorio.save(admin);
        }

        return mapper.map(admin);
    }

    @Override
    public void eliminarAdmin(Long id) throws Exception {

        findById(id);
        fotoServicioImp.eliminarFoto(findById(id).getFoto().getId());
        administradorRepositorio.deleteById(id);
    }

    @Override
    public void darBajaAdmin(Long id) throws Exception {

        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }

        Optional<Administrador> respuesta = administradorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Administrador admin = respuesta.get();
            admin.setAlta(Boolean.FALSE);

            administradorRepositorio.save(admin);
        }
    }

    @Override
    public void darAltaAdmin(Long id) throws Exception {

        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }

        Optional<Administrador> respuesta = administradorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Administrador admin = respuesta.get();
            admin.setAlta(Boolean.TRUE);

            administradorRepositorio.save(admin);
        }
    }

    @Override
    public Administrador findById(Long id) throws ResourceNotFoundException {

        Optional<Administrador> administrador = administradorRepositorio.findById(id);

        if (administrador.isPresent()) {
            return administrador.get();
        } else {
            throw new ResourceNotFoundException("Ese administrador no existe");
        }
    }

    @Override
    public AdministradorResponse findAdminById(Long id) throws ResourceNotFoundException {
        return mapper.map(findById(id));
    }

    @Override
    public ListAdministradorResponse ordenarAdminsPorNombre() {

        List<Administrador> administradores = administradorRepositorio.findByOrderByNombre();

        if (administradores.size() < 1) {
            throw new DataNotFoundException("No hay proveedores en la base de datos, agrega algunos.");
        }

        ListAdministradorResponse administradoresResponse = new ListAdministradorResponse();

        administradoresResponse.setAdministradores(mapper.map(administradores));

        return administradoresResponse;

    }

    @Override
    public ListAdministradorResponse ordenarAdminsPorNombreDesc() {

        List<Administrador> administradores = administradorRepositorio.findByOrderByNombreDesc();

        if (administradores.size() < 1) {
            throw new DataNotFoundException("No hay proveedores en la base de datos, agrega algunos.");
        }

        ListAdministradorResponse administradoresResponse = new ListAdministradorResponse();

        administradoresResponse.setAdministradores(mapper.map(administradores));

        return administradoresResponse;    
        
    }
}
