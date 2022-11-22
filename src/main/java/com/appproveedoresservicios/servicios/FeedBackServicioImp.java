package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.request.FeedBackRequest;
import com.appproveedoresservicios.dto.response.FeedBackResponse;
import com.appproveedoresservicios.dto.response.ListFeedBackResponse;
import com.appproveedoresservicios.entidades.FeedBack;
import com.appproveedoresservicios.excepciones.DataNotFoundException;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;
import com.appproveedoresservicios.mapper.FeedBackMapper;
import com.appproveedoresservicios.repositorios.FeedBackRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedBackServicioImp implements FeedBackServicio {

    @Autowired
    FeedBackMapper mapper;

    @Autowired
    FeedBackRepositorio feedbackRepositorio;

    @Override
    public FeedBackResponse crearFeedBack(FeedBackRequest request) {

        FeedBack feedback = mapper.map(request);

        feedbackRepositorio.save(feedback);

        return mapper.map(feedback);

    }

    @Override
    public ListFeedBackResponse listarFeedBacks() {
        List<FeedBack> feedbacks = feedbackRepositorio.findAll();

        if (feedbacks.size() < 1) {
            throw new DataNotFoundException("No hay feedbacks en la base de datos, agrega algunos.");
        }

        ListFeedBackResponse feedbacksResponse = new ListFeedBackResponse();

        feedbacksResponse.setFeedbacks(mapper.map(feedbacks));

        return feedbacksResponse;
    }

    @Override
    public FeedBack findById(Long id) throws ResourceNotFoundException {

        Optional<FeedBack> feedback = feedbackRepositorio.findById(id);

        if (feedback.isPresent()) {
            return feedback.get();
        } else {
            throw new ResourceNotFoundException("Este proveedor no existe");
        }

    }

    @Override
    public FeedBackResponse findFeedBackById(Long id) throws ResourceNotFoundException {
        return mapper.map(findById(id));
    }

    @Override
    public void eliminarFeedBack(Long id) {

        findById(id);
        feedbackRepositorio.deleteById(id);

    }

}
