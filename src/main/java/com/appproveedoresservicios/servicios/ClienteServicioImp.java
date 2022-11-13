package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.ClienteRequest;
import com.appproveedoresservicios.dto.ClienteResponse;
import com.appproveedoresservicios.entidades.Cliente;
import com.appproveedoresservicios.entidades.Foto;
import com.appproveedoresservicios.excepciones.AppExcepcion;
import com.appproveedoresservicios.mapper.ClienteMapper;
import com.appproveedoresservicios.repositorios.ClienteRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicioImp implements ClienteServicio {

    @Autowired
    ClienteRepositorio clienteRepositorio;

    @Autowired
    ClienteMapper mapper;

    @Autowired
    FotoServicioImp fotoServicioImp;

    @Override
    public ClienteResponse crearCliente(ClienteRequest request) throws Exception {

        validar(request);

        Cliente cliente = mapper.map(request);

        clienteRepositorio.save(cliente);

        return mapper.map(cliente);

    }

    @Override
    public ClienteResponse modificarCliente(ClienteRequest request, Long id) throws Exception {

        validar(request);

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Cliente cliente = respuesta.get();

            cliente.setNombre(request.getNombre());
            cliente.setCorreo(request.getCorreo());
            cliente.setClave(request.getClave());
            cliente.setContacto(request.getContacto());
            cliente.setBarrio(request.getBarrio());

            Long fotoId = null;
            if (request.getFoto() != null) {
                fotoId = cliente.getFoto().getId();
            }

            Foto foto = fotoServicioImp.actualizarFoto(request.getFoto(), fotoId);

            cliente.setFoto(foto);

            clienteRepositorio.save(cliente);

            return mapper.map(cliente);
        }
        return null;

    }

    @Override
    public Cliente findByID(Long id) throws Exception {
        Optional<Cliente> cliente = clienteRepositorio.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new Exception("Este proveedor no existe");
        }
    }

    @Override
    public Cliente eliminarCliente(Long id) throws Exception {
        findByID(id);
        clienteRepositorio.deleteById(id);
        return null;
    }

    @Override
    public void darBajaCliente(Long id) throws Exception {

        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            cliente.setAlta(Boolean.FALSE);

            clienteRepositorio.save(cliente);

        }

    }

    @Override
    public void darAltaCliente(Long id) throws Exception {

        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            cliente.setAlta(Boolean.TRUE);

            clienteRepositorio.save(cliente);

        }

    }

    private void validar(ClienteRequest request) throws AppExcepcion {

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

        if (request.getContacto().isEmpty() || request.getContacto() == null) {
            throw new AppExcepcion("El contacto no puede estar vacío.");
        }

        if (request.getBarrio().isEmpty() || request.getBarrio() == null) {
            throw new AppExcepcion("El barrio no puede estar vacío.");
        }

    }

}
