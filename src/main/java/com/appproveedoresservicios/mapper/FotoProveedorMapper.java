package com.appproveedoresservicios.mapper;

import com.appproveedoresservicios.dto.request.FotoProveedorRequest;
import com.appproveedoresservicios.dto.response.FotoProveedorResponse;
import com.appproveedoresservicios.entidades.Foto;
import com.appproveedoresservicios.entidades.FotoProveedor;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.servicios.FotoServicioImp;
import com.appproveedoresservicios.servicios.UsuarioServicioImp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProveedorMapper {
    
    @Autowired
    UsuarioServicioImp usuarioServicioImp;
    
    @Autowired
    FotoServicioImp fotoServicioImp;
    
    public FotoProveedor map(FotoProveedorRequest request){
        
        FotoProveedor fotoProveedor = new FotoProveedor();
        
        Proveedor proveedor = (Proveedor) usuarioServicioImp.findById(request.getIdProveedor());
        
        fotoProveedor.setProveedor(proveedor);
        
        Foto foto = fotoServicioImp.guardarFoto(request.getFoto());
    
        fotoProveedor.setFoto(foto);
        
        return fotoProveedor;
    }
    
    public FotoProveedorResponse map(FotoProveedor fotoProveedor){
    
        FotoProveedorResponse response = new FotoProveedorResponse();
        
        response.setId(fotoProveedor.getId());
        response.setIdProveedor(fotoProveedor.getProveedor().getId());
        response.setIdFoto(fotoProveedor.getFoto().getId());
    
        return response;
    }
    
    public List<FotoProveedorResponse> map (List<FotoProveedor> fotos){
        
        List<FotoProveedorResponse> listResponse = new  ArrayList<>();
        
        for (FotoProveedor fotoProveedor : fotos) {
            listResponse.add(map(fotoProveedor));
        }
        
        return listResponse;
    }
}
