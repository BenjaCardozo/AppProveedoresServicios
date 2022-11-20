package com.appproveedoresservicios.mapper;

import com.appproveedoresservicios.dto.request.TrabajoRequest;
import com.appproveedoresservicios.dto.response.TrabajoResponse;
import com.appproveedoresservicios.entidades.Cliente;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.entidades.Trabajo;
import com.appproveedoresservicios.servicios.UsuarioServicioImp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrabajoMapper {
    
    @Autowired
    UsuarioServicioImp usuarioServicioImp;
    
    public Trabajo map(TrabajoRequest trabajoRequest){
        
        Trabajo trabajo = new Trabajo();
        
        Cliente cliente = (Cliente) usuarioServicioImp.findById(trabajoRequest.getIdCliente());
        Proveedor proveedor = (Proveedor) usuarioServicioImp.findById(trabajoRequest.getIdProveedor());
        
        trabajo.setProveedor(proveedor);
        trabajo.setCliente(cliente);
        trabajo.setFechaInicio(LocalDate.now());
        
        return trabajo;
    }
    
    public TrabajoResponse map(Trabajo trabajo){
        
        TrabajoResponse response = new TrabajoResponse();
                      
        response.setId(trabajo.getId());
        response.setIdProveedor(trabajo.getProveedor().getId());
        response.setIdCliente(trabajo.getCliente().getId());
        response.setFechaInicio(trabajo.getFechaInicio());
        response.setFechaFin(trabajo.getFechaFin());
        
        return response;
    }
    
    public List<TrabajoResponse> map (List<Trabajo> trabajos){
        
        List<TrabajoResponse> listResponse = new  ArrayList<>();
        
        for (Trabajo trabajo : trabajos) {
            listResponse.add(map(trabajo));
        }
        
        return listResponse;
    }    
    
}
