package com.appproveedoresservicios.mapper;

import com.appproveedoresservicios.dto.request.SolicitudTrabajoRequest;
import com.appproveedoresservicios.dto.response.SolicitudTrabajoResponse;
import com.appproveedoresservicios.entidades.Cliente;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.entidades.SolicitudTrabajo;
import com.appproveedoresservicios.repositorios.SolicitudTrabajoRepositorio;
import com.appproveedoresservicios.servicios.UsuarioServicioImp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolicitudTrabajoMapper {

    @Autowired
    SolicitudTrabajoRepositorio solicitudTrabajoRepositorio;

    @Autowired
    UsuarioServicioImp usuarioServicioImp;

    public SolicitudTrabajo map(SolicitudTrabajoRequest solicitudTrabajoRequest) {

        SolicitudTrabajo solicitudTrabajo = new SolicitudTrabajo();

        Cliente cliente = (Cliente) usuarioServicioImp.findById(solicitudTrabajoRequest.getIdCliente());

        Proveedor proveedor = (Proveedor) usuarioServicioImp.findById(solicitudTrabajoRequest.getIdProveedor());

        solicitudTrabajo.setProveedor(proveedor);
        solicitudTrabajo.setCliente(cliente);
        solicitudTrabajo.setSolicitud(solicitudTrabajoRequest.getSolicitud());

        return solicitudTrabajo;

    }

    public SolicitudTrabajoResponse map(SolicitudTrabajo solicitudTrabajo) {

        SolicitudTrabajoResponse response = new SolicitudTrabajoResponse();

        response.setId(solicitudTrabajo.getId());
        response.setIdProveedor(solicitudTrabajo.getProveedor().getId());
        response.setIdCliente(solicitudTrabajo.getCliente().getId());
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
