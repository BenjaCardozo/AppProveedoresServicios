package com.appproveedoresservicios.mapper;

import com.appproveedoresservicios.dto.ClienteRequest;
import com.appproveedoresservicios.dto.ClienteResponse;
import com.appproveedoresservicios.entidades.Cliente;
import com.appproveedoresservicios.entidades.Foto;
import com.appproveedoresservicios.enums.Rol;
import com.appproveedoresservicios.servicios.FotoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    @Autowired
    FotoServicio fotoServicio;

    public Cliente map(ClienteRequest clienteRequest) {

        Cliente cliente = new Cliente();

        //atributos de persona
        cliente.setNombre(clienteRequest.getNombre());
        cliente.setCorreo(clienteRequest.getCorreo());
        cliente.setClave(clienteRequest.getClave());
        cliente.setBarrio(clienteRequest.getBarrio());
        Foto foto = fotoServicio.guardarFoto(clienteRequest.getFoto());
        cliente.setFoto(foto);
        cliente.setAlta(true);
        cliente.setRol(Rol.CLIENTE);

        //atributos propios de cliente
        cliente.setContacto(clienteRequest.getContacto());

        return cliente;
    }

    public ClienteResponse map(Cliente cliente) {

        ClienteResponse response = new ClienteResponse();

        response.setId(cliente.getId());
        response.setNombre(cliente.getNombre());
        response.setCorreo(cliente.getCorreo());
        response.setBarrio(cliente.getBarrio());
        response.setAlta(cliente.getAlta());
        response.setRol(cliente.getRol());

        response.setContacto(cliente.getContacto());

        return response;
    }
}
