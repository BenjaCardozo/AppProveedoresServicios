package com.appproveedoresservicios.mapper;

import com.appproveedoresservicios.dto.request.FeedBackRequest;
import com.appproveedoresservicios.dto.response.FeedBackResponse;
import com.appproveedoresservicios.entidades.FeedBack;
import com.appproveedoresservicios.entidades.Trabajo;
import com.appproveedoresservicios.servicios.TrabajoServicioImp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedBackMapper {

    @Autowired
    TrabajoServicioImp trabajoServicioImp;
    
    public FeedBack map(FeedBackRequest feedbackRequest) {

        Trabajo trabajo = trabajoServicioImp.findById(feedbackRequest.getIdTrabajo());
        
        FeedBack feedback = new FeedBack();

        //atributos de feedback
        feedback.setCalificacion(feedbackRequest.getCalificacion());
        feedback.setComentario(feedbackRequest.getComentario());
        feedback.setTrabajo(trabajo);
        
        
        return feedback;
    }

    public FeedBackResponse map(FeedBack feedback) {
        
        FeedBackResponse response = new FeedBackResponse();

        response.setId(feedback.getId());
        response.setCalificacion(feedback.getCalificacion());
        response.setComentario(feedback.getComentario());
        response.setIdTrabajo(feedback.getTrabajo().getId());
        
        return response;
    }

    public List<FeedBackResponse> map(List<FeedBack> feedbacks) {

        List<FeedBackResponse> listResponse = new ArrayList<>();

        for (FeedBack feedback : feedbacks) {
            listResponse.add(map(feedback));
        }

        return listResponse;
    }

}
