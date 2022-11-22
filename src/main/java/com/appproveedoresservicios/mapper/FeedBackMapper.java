package com.appproveedoresservicios.mapper;

import com.appproveedoresservicios.dto.request.FeedBackRequest;
import com.appproveedoresservicios.dto.response.FeedBackResponse;
import com.appproveedoresservicios.entidades.FeedBack;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FeedBackMapper {

    public FeedBack map(FeedBackRequest feedbackRequest) {

        FeedBack feedback = new FeedBack();

        //atributos de feedback
        feedback.setCalificacion(feedbackRequest.getCalificacion());
        feedback.setComentario(feedbackRequest.getComentario());

        return feedback;
    }

    public FeedBackResponse map(FeedBack feedback) {

        FeedBackResponse response = new FeedBackResponse();

        response.setId(feedback.getId());
        response.setCalificacion(feedback.getCalificacion());
        response.setComentario(feedback.getComentario());

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
