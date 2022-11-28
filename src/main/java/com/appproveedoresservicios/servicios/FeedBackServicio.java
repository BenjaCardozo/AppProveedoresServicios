package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.request.FeedBackRequest;
import com.appproveedoresservicios.dto.response.FeedBackResponse;
import com.appproveedoresservicios.dto.response.ListFeedBackResponse;
import com.appproveedoresservicios.entidades.FeedBack;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;

public interface FeedBackServicio {

    FeedBackResponse crearFeedBack(FeedBackRequest request);

    FeedBackResponse actualizarFeedBack(FeedBackRequest request, Long id) throws Exception ;
    
    void eliminarFeedBack(Long id);

    FeedBack findById(Long id) throws ResourceNotFoundException;
    
    FeedBackResponse findFeedBackById(Long id) throws ResourceNotFoundException; 

    ListFeedBackResponse listarFeedBacks();
    
    FeedBack buscarFeedBackPorTrabajo(Long id);
    
}
