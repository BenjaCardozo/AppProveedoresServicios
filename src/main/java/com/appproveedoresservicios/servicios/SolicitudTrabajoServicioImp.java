package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.request.SolicitudTrabajoRequest;
import com.appproveedoresservicios.dto.response.ListSolicitudTrabajoResponse;
import com.appproveedoresservicios.dto.response.SolicitudTrabajoResponse;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.entidades.SolicitudTrabajo;
import com.appproveedoresservicios.excepciones.DataNotFoundException;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;
import com.appproveedoresservicios.mapper.SolicitudTrabajoMapper;
import com.appproveedoresservicios.repositorios.SolicitudTrabajoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitudTrabajoServicioImp implements SolicitudTrabajoServicio {

    @Autowired
    SolicitudTrabajoMapper mapper;

    @Autowired
    SolicitudTrabajoRepositorio solicitudTrabajoRepositorio;
    
    @Autowired
    ProveedorServicioImp proveedorServicioImp;

    @Override
    public SolicitudTrabajoResponse crearSolicitud(SolicitudTrabajoRequest request) {
        SolicitudTrabajo solicitudTrabajo = mapper.map(request);

        solicitudTrabajoRepositorio.save(solicitudTrabajo);

        return mapper.map(solicitudTrabajo);
    }

    @Override
    public SolicitudTrabajo findById(Long id) throws ResourceNotFoundException {

        Optional<SolicitudTrabajo> solicitudTrabajo = solicitudTrabajoRepositorio.findById(id);

        if (solicitudTrabajo.isPresent()) {
            return solicitudTrabajo.get();
        } else {
            throw new ResourceNotFoundException("Ese trabajo no existe");
        }

    }

    @Override
    public SolicitudTrabajoResponse findSolicitudById(Long id) throws ResourceNotFoundException {

        return mapper.map(findById(id));

    }

    @Override
    public void eliminarTrabajoSolicitud(Long id) throws Exception {

        findById(id);
        solicitudTrabajoRepositorio.deleteById(id);

    }

    @Override
    public ListSolicitudTrabajoResponse listarSolicitudes() {

        List<SolicitudTrabajo> solicitudesTrabajo = solicitudTrabajoRepositorio.findAll();

        if (solicitudesTrabajo.size() < 1) {
            throw new DataNotFoundException("No hay solicitudes de trabajo en la base de datos, agrega algunos.");
        }

        ListSolicitudTrabajoResponse solicitudesResponse = new ListSolicitudTrabajoResponse();

        solicitudesResponse.setSolicitudesTrabajo(mapper.map(solicitudesTrabajo));

        return solicitudesResponse;

    }

    @Override
    public ListSolicitudTrabajoResponse buscarSolicitudesPorProveedor(Long idProveedor) throws ResourceNotFoundException {

        Proveedor proveedor = proveedorServicioImp.findById(idProveedor);

        List<SolicitudTrabajo> solicitudesTrabajo = solicitudTrabajoRepositorio.findByProveedor(proveedor);

        if (solicitudesTrabajo.size() < 1) {
            throw new DataNotFoundException("Este proveedor no posee ningun trabajo, agrega algunos.");
        }

        ListSolicitudTrabajoResponse solicitudesResponse = new ListSolicitudTrabajoResponse();

        solicitudesResponse.setSolicitudesTrabajo(mapper.map(solicitudesTrabajo));

        return solicitudesResponse;

    }

}
