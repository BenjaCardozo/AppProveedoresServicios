package com.appproveedoresservicios.mapper;

import com.appproveedoresservicios.dto.request.SolicitudTrabajoRequest;
import com.appproveedoresservicios.dto.response.SolicitudTrabajoResponse;
import com.appproveedoresservicios.entidades.SolicitudTrabajo;
import com.appproveedoresservicios.repositorios.SolicitudTrabajoRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class SolicitudTrabajoMapper {

    @Autowired
    SolicitudTrabajoRepositorio solicitudTrabajoRepositorio;

    public SolicitudTrabajo map(SolicitudTrabajoRequest solicitudTrabajoRequest) {

        SolicitudTrabajo solicitudTrabajo = new SolicitudTrabajo();

        solicitudTrabajo.setIdProveedor(solicitudTrabajoRequest.getIdProveedor());
        solicitudTrabajo.setIdCliente(solicitudTrabajoRequest.getIdCliente());
        solicitudTrabajo.setSolicitud(solicitudTrabajoRequest.getSolicitud());

        return solicitudTrabajo;

    }

    public SolicitudTrabajoResponse map(SolicitudTrabajo solicitudTrabajo) {

        SolicitudTrabajoResponse response = new SolicitudTrabajoResponse();

        response.setId(solicitudTrabajo.getId());
        response.setIdProveedor(solicitudTrabajo.getIdProveedor());
        response.setIdCliente(solicitudTrabajo.getIdCliente());
        response.setSolicitud(solicitudTrabajo.getSolicitud());

        return response;
    }

    public List<SolicitudTrabajoResponse> map(List<SolicitudTrabajo> solicitudesTrabajo) {

        List<SolicitudTrabajoResponse> listResponse = new ArrayList<>();

        for (SolicitudTrabajo solicitudTrabajo : solicitudesTrabajo) {
            listResponse.add(map(solicitudTrabajo));
        }

        return listResponse;
    }

}
