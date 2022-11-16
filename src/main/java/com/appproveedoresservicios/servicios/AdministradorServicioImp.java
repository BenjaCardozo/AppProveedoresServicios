package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.AdministradorRequest;
import com.appproveedoresservicios.dto.AdministradorResponse;
import com.appproveedoresservicios.entidades.Administrador;
import com.appproveedoresservicios.entidades.Foto;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;
import com.appproveedoresservicios.mapper.AdministradorMapper;
import com.appproveedoresservicios.repositorios.AdministradorRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServicioImp implements AdministradorServicio {

    @Autowired
    AdministradorRepositorio administradorRepositorio;

    @Autowired
    AdministradorMapper mapper;

    @Autowired
    FotoServicioImp fotoServicioImp;

    @Override
    public AdministradorResponse crearAdmin(AdministradorRequest request) {
        
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
            admin.setClave(request.getClave());
            admin.setBarrio(request.getBarrio());

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
            throw new ResourceNotFoundException("Este administrador no existe");
        }
    }

    @Override
    public AdministradorResponse findAdminById(Long id) throws ResourceNotFoundException {
        return mapper.map(findById(id));
    }
}
