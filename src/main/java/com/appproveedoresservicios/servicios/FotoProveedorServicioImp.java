package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.request.FotoProveedorRequest;
import com.appproveedoresservicios.dto.response.FotoProveedorResponse;
import com.appproveedoresservicios.dto.response.ListFotoProveedorResponse;
import com.appproveedoresservicios.entidades.Foto;
import com.appproveedoresservicios.entidades.FotoProveedor;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.excepciones.DataNotFoundException;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;
import com.appproveedoresservicios.mapper.FotoProveedorMapper;
import com.appproveedoresservicios.repositorios.FotoProveedorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoProveedorServicioImp implements FotoProveedorServicio{
    
    @Autowired
    FotoProveedorRepositorio fotoProveedorRepositorio;

    @Autowired
    FotoProveedorMapper mapper;
    
    @Autowired
    FotoServicioImp fotoServicioImp;
    
    @Autowired
    ProveedorServicioImp proveedorServicioImp;
    
    @Override
    public FotoProveedorResponse crearFotoProveedor(FotoProveedorRequest request) {
        
        FotoProveedor fotoProveedor = mapper.map(request);
        
        fotoProveedorRepositorio.save(fotoProveedor);
        
        return mapper.map(fotoProveedor);
    }

    @Override
    public FotoProveedorResponse actualizarFotoProveedor(FotoProveedorRequest request, Long id) {
        
        Optional<FotoProveedor> respuesta = fotoProveedorRepositorio.findById(id);
        
        FotoProveedor fotoProveedor = null;
        
        if(respuesta.isPresent()){
            fotoProveedor = respuesta.get();
            
            Long fotoId = null;
            if (fotoProveedor.getFoto() != null) {
                fotoId = fotoProveedor.getFoto().getId();
            }

            Foto foto = fotoServicioImp.actualizarFoto(request.getFoto(), fotoId);
            fotoProveedor.setFoto(foto);
            
            fotoProveedorRepositorio.save(fotoProveedor);
        }
        return mapper.map(fotoProveedor);
    }
    
    public FotoProveedor findById(Long id) throws ResourceNotFoundException {

        Optional<FotoProveedor> respuesta = fotoProveedorRepositorio.findById(id);

        if (respuesta.isPresent()) {
            return respuesta.get();
        } else {
            throw new ResourceNotFoundException("Ese fotoProveedor no existe");
        }
    }

    @Override
    public void eliminarFotoProveedor(Long id) {
        
        findById(id);
        fotoServicioImp.eliminarFoto(findById(id).getFoto().getId());
        fotoProveedorRepositorio.deleteById(id);
    }

    @Override
    public ListFotoProveedorResponse listarFotosProveedor(Long idProveedor) {
        
        Proveedor proveedor = proveedorServicioImp.findById(idProveedor);
        
        List<FotoProveedor> fotos = fotoProveedorRepositorio.findByProveedor(proveedor);
        
        if (fotos.size() < 1) {
            throw new DataNotFoundException("Este proveedor no posee ninguna fotos, agrega algunas.");
        }
        
        ListFotoProveedorResponse fotoProveedorResponse = new ListFotoProveedorResponse();
        
        fotoProveedorResponse.setFotosProveedor(mapper.map(fotos));
        
        return fotoProveedorResponse;
    }
}
