package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.response.ListTrabajoResponse;
import com.appproveedoresservicios.dto.request.TrabajoRequest;
import com.appproveedoresservicios.dto.response.TrabajoResponse;
import com.appproveedoresservicios.entidades.FeedBack;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.entidades.Trabajo;
import com.appproveedoresservicios.excepciones.DataNotFoundException;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;
import com.appproveedoresservicios.mapper.TrabajoMapper;
import com.appproveedoresservicios.repositorios.TrabajoRepositorio;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajoServicioImp implements TrabajoServicio {

    @Autowired
    TrabajoMapper mapper;

    @Autowired
    TrabajoRepositorio trabajoRepositorio;

    @Autowired
    ProveedorServicioImp proveedorServicioImp;
    
    @Autowired
    FeedBackServicioImp feedbackServicioImp;
    
    @Override
    public TrabajoResponse crearTrabajo(TrabajoRequest request) {

        Trabajo trabajo = mapper.map(request);
        
        trabajoRepositorio.save(trabajo);

        return mapper.map(trabajo);
    }

    @Override
    public TrabajoResponse trabajoConFechaFinal(Long id){
        
        Optional<Trabajo> respuesta = trabajoRepositorio.findById(id);

        Trabajo trabajo = null;

        if (respuesta.isPresent()) {

            trabajo = respuesta.get();

            trabajo.setFechaFin(LocalDate.now());
            
            FeedBack feedback = feedbackServicioImp.buscarFeedBackPorTrabajo(id);
                        
            trabajo.setFeedback(feedback);
            
            trabajo.setAlta(Boolean.FALSE);
            
            trabajoRepositorio.save(trabajo);
        }

        return mapper.map(trabajo);
    }

    @Override
    public void eliminarTrabajo(Long id) {

        findById(id);
        trabajoRepositorio.deleteById(id);
    }

    @Override
    public Trabajo findById(Long id) throws ResourceNotFoundException {

        Optional<Trabajo> trabajo = trabajoRepositorio.findById(id);

        if (trabajo.isPresent()) {
            return trabajo.get();
        } else {
            throw new ResourceNotFoundException("Ese trabajo no existe");
        }

    }

    @Override
    public TrabajoResponse findTrabajoById(Long id) throws ResourceNotFoundException {

        return mapper.map(findById(id));

    }

    @Override
    public ListTrabajoResponse listarTrabajos() {

        List<Trabajo> trabajos = trabajoRepositorio.findAll();
        
        if (trabajos.size() < 1) {
            throw new DataNotFoundException("No hay trabajos en la base de datos, agrega algunos.");
        }

        ListTrabajoResponse trabajosResponse = new ListTrabajoResponse();

        trabajosResponse.setTrabajos(mapper.map(trabajos));

        return trabajosResponse;

    }

    @Override
    public void darBajaTrabajo(Long id) throws Exception {

        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }

        Optional<Trabajo> respuesta = trabajoRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Trabajo trabajo = respuesta.get();
            trabajo.setAlta(Boolean.FALSE);

            trabajoRepositorio.save(trabajo);
        }
    }

    @Override
    public void darAltaTrabajo(Long id) throws Exception {

        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }

        Optional<Trabajo> respuesta = trabajoRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Trabajo trabajo = respuesta.get();
            trabajo.setAlta(Boolean.TRUE);

            trabajoRepositorio.save(trabajo);
        }
    }

    @Override
    public ListTrabajoResponse listarTrabajoPorProveedor(Long idProveedor) {

    Proveedor proveedor = proveedorServicioImp.findById(idProveedor);
        
        List<Trabajo> trabajos = trabajoRepositorio.findByProveedor(proveedor);

        if (trabajos.size() < 1) {
            throw new DataNotFoundException("Este proveedor no posee ningun trabajo, agrega algunos.");
        }

        ListTrabajoResponse trabajosResponse = new ListTrabajoResponse();

        trabajosResponse.setTrabajos(mapper.map(trabajos));

        return trabajosResponse;
    }
}
