package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.request.ModeradorRequest;
import com.appproveedoresservicios.dto.response.ListModeradorResponse;
import com.appproveedoresservicios.dto.response.ModeradorResponse;
import com.appproveedoresservicios.entidades.Administrador;
import com.appproveedoresservicios.entidades.Foto;
import com.appproveedoresservicios.entidades.Moderador;
import com.appproveedoresservicios.excepciones.DataNotFoundException;
import com.appproveedoresservicios.excepciones.EmailAlreadyInUseException;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;
import com.appproveedoresservicios.mapper.ModeradorMapper;
import com.appproveedoresservicios.repositorios.ModeradorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ModeradorServicioImp implements ModeradorServicio {

    @Autowired
    ModeradorRepositorio moderadorRepositorio;

    @Autowired
    ModeradorMapper mapper;

    @Autowired
    FotoServicioImp fotoServicioImp;

    @Autowired
    UsuarioServicioImp usuarioServicioImp;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public ModeradorResponse crearModerador(ModeradorRequest request) throws EmailAlreadyInUseException {

        if (usuarioServicioImp.buscaPorCorreo(request.getCorreo())) {
            throw new EmailAlreadyInUseException("Ese correo ya está en uso, ingresa otro.");
        }

        Moderador moderador = mapper.map(request);

        moderadorRepositorio.save(moderador);

        return mapper.map(moderador);
    }

    @Override
    public ModeradorResponse actualizarModerador(ModeradorRequest request, Long id) {

        Optional<Moderador> respuesta = moderadorRepositorio.findById(id);
        Moderador moderador = null;

        if (respuesta.isPresent()) {

            moderador = respuesta.get();

            moderador.setNombre(request.getNombre());
            moderador.setCorreo(request.getCorreo());
            moderador.setClave(passwordEncoder.encode(request.getClave()));

            Long fotoId = null;
            if (moderador.getFoto() != null) {
                fotoId = moderador.getFoto().getId();
            }

            Foto foto = fotoServicioImp.actualizarFoto(request.getFoto(), fotoId);
            moderador.setFoto(foto);

            moderadorRepositorio.save(moderador);
        }

        return mapper.map(moderador);

    }

    @Override
    public void eliminarModerador(Long id) throws Exception {

        findById(id);
        if(findById(id).getFoto() !=null){
            fotoServicioImp.eliminarFoto(findById(id).getFoto().getId());
        }
        moderadorRepositorio.deleteById(id);

    }

    @Override
    public void darBajaModerador(Long id) throws Exception {

        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }

        Optional<Moderador> respuesta = moderadorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Moderador moderador = respuesta.get();
            moderador.setAlta(Boolean.FALSE);

            moderadorRepositorio.save(moderador);
        }

    }

    @Override
    public void darAltaModerador(Long id) throws Exception {

        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }

        Optional<Moderador> respuesta = moderadorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Moderador moderador = respuesta.get();
            moderador.setAlta(Boolean.TRUE);

            moderadorRepositorio.save(moderador);
        }

    }

    @Override
    public Moderador findById(Long id) {

        Optional<Moderador> moderador = moderadorRepositorio.findById(id);

        if (moderador.isPresent()) {
            return moderador.get();
        } else {
            throw new ResourceNotFoundException("Ese moderador no existe");
        }

    }

    @Override
    public ModeradorResponse findModeradorById(Long id) {
        return mapper.map(findById(id));
    }

    /*@Override
    public ListModeradorResponse ordenarModeradoresPorNombres() {

        List<Moderador> moderadores = moderadorRepositorio.findByOrderByNombre();

        if (moderadores.size() < 1) {
            throw new DataNotFoundException("No hay moderadores en la base de datos, agrega algunos.");
        }

        ListModeradorResponse moderadoresResponse = new ListModeradorResponse();

        moderadoresResponse.setModeradores(mapper.map(moderadores));

        return moderadoresResponse;


    }

    @Override
    public ListModeradorResponse ordenarModeradoresPorNombresDesc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

}
