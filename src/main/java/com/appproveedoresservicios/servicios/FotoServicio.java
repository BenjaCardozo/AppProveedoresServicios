package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.entidades.Foto;
import org.springframework.web.multipart.MultipartFile;

public interface FotoServicio {
    
    Foto guardarFoto(MultipartFile archivo);
    Foto actualizarFoto(MultipartFile archivo, Long idFoto);
    Foto findFotoById(Long idFoto);
    void eliminarFoto(Long idFoto);
}
