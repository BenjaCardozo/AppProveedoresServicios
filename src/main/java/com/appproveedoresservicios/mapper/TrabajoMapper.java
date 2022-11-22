package com.appproveedoresservicios.mapper;

import com.appproveedoresservicios.dto.TrabajoRequest;
import com.appproveedoresservicios.dto.TrabajoResponse;
import com.appproveedoresservicios.entidades.Trabajo;
import com.appproveedoresservicios.servicios.TrabajoServicioImp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrabajoMapper {

    @Autowired
    TrabajoServicioImp trabajoServicioImp;

    public Trabajo map(TrabajoRequest trabajoRequest) {

        Trabajo trabajo = new Trabajo();

        trabajo.setProveedor(trabajoRequest.getProveedor());
        trabajo.setCliente(trabajoRequest.getCliente());
        trabajo.setFechaInicio(trabajoRequest.getFechaInicio());
        trabajo.setFechaFin(trabajoRequest.getFechaFin());

        return trabajo;
    }

    public TrabajoResponse map(Trabajo trabajo) {

        TrabajoResponse response = new TrabajoResponse();

        response.setId(trabajo.getId());
        response.setProveedor(trabajo.getProveedor());
        response.setCliente(trabajo.getCliente());
        response.setFechaInicio(trabajo.getFechaInicio());
        response.setFechaFin(trabajo.getFechaFin());

        return response;
    }

    public List<TrabajoResponse> map(List<Trabajo> trabajos) {

        List<TrabajoResponse> listResponse = new ArrayList<>();

        for (Trabajo trabajo : trabajos) {
            listResponse.add(map(trabajo));
        }

        return listResponse;
    }

}
