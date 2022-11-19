package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.ClienteRequest;
import com.appproveedoresservicios.dto.ClienteResponse;
import com.appproveedoresservicios.dto.ListClienteResponse;
import com.appproveedoresservicios.entidades.Cliente;
import com.appproveedoresservicios.entidades.Foto;
import com.appproveedoresservicios.excepciones.DataNotFoundException;
import com.appproveedoresservicios.excepciones.EmailAlreadyInUseException;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;
import com.appproveedoresservicios.mapper.ClienteMapper;
import com.appproveedoresservicios.repositorios.ClienteRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicioImp implements ClienteServicio {

    @Autowired
    ClienteRepositorio clienteRepositorio;

    @Autowired
    ClienteMapper mapper;

    @Autowired
    FotoServicioImp fotoServicioImp;
    
    @Autowired
    UsuarioServicioImp usuarioServicioImp;
    
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public ClienteResponse crearCliente(ClienteRequest request) throws EmailAlreadyInUseException{

        if(usuarioServicioImp.buscaPorCorreo(request.getCorreo())){
           throw new EmailAlreadyInUseException("Ese correo ya está en uso, ingresa otro.");
        }
        
        Cliente cliente = mapper.map(request);

        clienteRepositorio.save(cliente);

        return mapper.map(cliente);

    }

    @Override
    public ClienteResponse modificarCliente(ClienteRequest request, Long id) throws Exception {

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        Cliente cliente = null;

        if (respuesta.isPresent()) {

            cliente = respuesta.get();

            cliente.setNombre(request.getNombre());
            cliente.setCorreo(request.getCorreo());
            cliente.setClave(passwordEncoder.encode(request.getClave()));
            cliente.setContacto(request.getContacto());
            cliente.setBarrio(request.getBarrio());

            Long fotoId = null;
            if (request.getFoto() != null) {
                fotoId = cliente.getFoto().getId();
            }

            Foto foto = fotoServicioImp.actualizarFoto(request.getFoto(), fotoId);

            cliente.setFoto(foto);

            clienteRepositorio.save(cliente);
        }

        return mapper.map(cliente);
    }

    @Override
    public ListClienteResponse listarClientes() {

        List<Cliente> clientes = clienteRepositorio.findAll();

        if (clientes.size() < 1) {
            throw new DataNotFoundException("No hay clientes en la base de datos, agrega algunos.");
        }
        ListClienteResponse clientesResponse = new ListClienteResponse();

        clientesResponse.setClientes(mapper.map(clientes));

        return clientesResponse;
    }

        @Override
    public ListClienteResponse buscarClientePorBarrio(String barrio) throws ResourceNotFoundException {
        
        List<Cliente> clientes = clienteRepositorio.findByBarrio(barrio);
        
        if (clientes.size() < 1) {
            throw new DataNotFoundException("No hay proveedores en la base de datos, agrega algunos.");
        }
        ListClienteResponse clientesResponse = new ListClienteResponse();

        clientesResponse.setClientes(mapper.map(clientes));

        return clientesResponse;
    }
    
    @Override
    public Cliente findById(Long id) throws ResourceNotFoundException {
        Optional<Cliente> cliente = clienteRepositorio.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new ResourceNotFoundException("Este cliente no existe.");
        }
    }

    @Override
    public ClienteResponse findClienteById(Long id) throws ResourceNotFoundException {
        return mapper.map(findById(id));
    }

    @Override
    public Cliente eliminarCliente(Long id) throws Exception {
        findById(id);
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
}
