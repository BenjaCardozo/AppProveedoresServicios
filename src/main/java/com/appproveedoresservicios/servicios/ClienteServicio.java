package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.request.ClienteRequest;
import com.appproveedoresservicios.dto.response.ClienteResponse;
import com.appproveedoresservicios.dto.response.ListClienteResponse;
import com.appproveedoresservicios.entidades.Cliente;
import com.appproveedoresservicios.excepciones.EmailAlreadyInUseException;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;

public interface ClienteServicio {

    ClienteResponse crearCliente(ClienteRequest request) throws EmailAlreadyInUseException;

    ClienteResponse modificarCliente(ClienteRequest request, Long id) throws Exception;

    Cliente findById(Long id) throws ResourceNotFoundException;

    ClienteResponse findClienteById(Long id) throws ResourceNotFoundException;

    Cliente eliminarCliente(Long id) throws Exception;

    void darBajaCliente(Long id) throws Exception;

    void darAltaCliente(Long id) throws Exception;

    ListClienteResponse buscarClientePorBarrio(String barrio) throws ResourceNotFoundException;

    ListClienteResponse listarClientes();
    
}
