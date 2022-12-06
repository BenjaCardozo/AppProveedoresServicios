package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.request.FotoProveedorRequest;
import com.appproveedoresservicios.dto.response.FotoProveedorResponse;
import com.appproveedoresservicios.dto.response.ListFotoProveedorResponse;

public interface FotoProveedorServicio {
    
    FotoProveedorResponse crearFotoProveedor(FotoProveedorRequest request);
    
    FotoProveedorResponse actualizarFotoProveedor(FotoProveedorRequest request, Long id);
    
    void eliminarFotoProveedor(Long id);
    
    ListFotoProveedorResponse listarFotosProveedor(Long idProveedor);
}
