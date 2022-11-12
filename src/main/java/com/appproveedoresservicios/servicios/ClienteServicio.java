package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.ClienteRequest;
import com.appproveedoresservicios.dto.ClienteResponse;
import com.appproveedoresservicios.entidades.Cliente;

public interface ClienteServicio {
    
    ClienteResponse crearCliente(ClienteRequest request) throws Exception ;
    
    ClienteResponse modificarCliente(ClienteRequest request, Long id) throws Exception ;
    
    Cliente findByID(Long id) throws Exception;
    
    Cliente eliminarCliente(Long id) throws Exception;
    
    void darBajaCliente(Long id) throws Exception ;
    
    void darAltaCliente(Long id) throws Exception ;
    
}
