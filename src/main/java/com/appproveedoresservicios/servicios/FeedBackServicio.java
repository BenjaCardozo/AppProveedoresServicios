package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.FeedBackRequest;
import com.appproveedoresservicios.dto.FeedBackResponse;
import com.appproveedoresservicios.dto.ListFeedBackResponse;
import com.appproveedoresservicios.entidades.FeedBack;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;

public interface FeedBackServicio {

    FeedBackResponse crearFeedBack(FeedBackRequest request);

    void eliminarFeedBack(Long id);

    FeedBack findById(Long id) throws ResourceNotFoundException;
    
    FeedBackResponse findFeedBackById(Long id) throws ResourceNotFoundException; 

    ListFeedBackResponse listarFeedBacks();

}
