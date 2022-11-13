package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.entidades.Cliente;

public interface ClienteServicio {
    
    void crearCliente();
    
    void modificarClienter();
    
    void eliminarCliente();
    
    void darBajaCliente();
    
    void darAltaCliente();
    
    Cliente findByID();
    
}
