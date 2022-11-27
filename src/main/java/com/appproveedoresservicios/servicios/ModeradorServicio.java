package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.request.ModeradorRequest;
import com.appproveedoresservicios.dto.response.ListModeradorResponse;
import com.appproveedoresservicios.dto.response.ModeradorResponse;
import com.appproveedoresservicios.entidades.Moderador;
import com.appproveedoresservicios.excepciones.EmailAlreadyInUseException;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;

public interface ModeradorServicio {

    ModeradorResponse crearModerador(ModeradorRequest request) throws EmailAlreadyInUseException;

    ModeradorResponse actualizarModerador(ModeradorRequest request, Long id);

    void eliminarModerador(Long id) throws Exception;

    void darBajaModerador(Long id) throws Exception;

    void darAltaModerador(Long id) throws Exception;

    Moderador findById(Long id) throws ResourceNotFoundException;

    ModeradorResponse findModeradorById(Long id) throws ResourceNotFoundException;

    /*ListModeradorResponse ordenarModeradoresPorNombres();

    ListModeradorResponse ordenarModeradoresPorNombresDesc();*/

}
