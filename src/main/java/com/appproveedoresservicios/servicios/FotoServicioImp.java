package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.entidades.Foto;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;
import com.appproveedoresservicios.repositorios.FotoRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicioImp implements FotoServicio{

    @Autowired
    private FotoRepositorio fotoRepositorio;
    
    @Override
    public Foto guardarFoto(MultipartFile archivo) {
        if(archivo!=null && !archivo.isEmpty()){
            try {
                Foto foto = new Foto();
                foto.setNombre(archivo.getName());
                foto.setMime(archivo.getContentType());
                foto.setContenido(archivo.getBytes());

                return fotoRepositorio.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Foto actualizarFoto(MultipartFile archivo, Long idFoto) {
        if(archivo!=null){
            try {
                Foto foto = new Foto();

                if(idFoto!=null){
                    Optional<Foto> respuesta = fotoRepositorio.findById(idFoto);
                    if(respuesta.isPresent()){
                        foto = respuesta.get();
                    }
                }
                
                foto.setNombre(archivo.getName());
                foto.setMime(archivo.getContentType());
                foto.setContenido(archivo.getBytes());

                return fotoRepositorio.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Foto findFotoById(Long idFoto) {
        Optional<Foto> foto = fotoRepositorio.findById(idFoto);
        if (foto.isPresent()) {
            return foto.get();
        } else {
            throw new ResourceNotFoundException("Esa foto no existe");
        }
    }

    @Override
    public void eliminarFoto(Long idFoto) {
        findFotoById(idFoto);
        fotoRepositorio.deleteById(idFoto);
    }
}
