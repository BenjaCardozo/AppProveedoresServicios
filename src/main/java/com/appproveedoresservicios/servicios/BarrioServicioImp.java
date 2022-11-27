package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.dto.response.ListBarrioResponse;
import com.appproveedoresservicios.enums.Barrio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BarrioServicioImp implements BarrioServicio {

    @Override
    public ListBarrioResponse listarBarrios() {
        
        List<Barrio> barrios = new ArrayList();
        
        for (Barrio value : Barrio.values()) {
            
            barrios.add(value);
            
        }
        
        ListBarrioResponse listBarrioResponse = new ListBarrioResponse();
        
        listBarrioResponse.setBarrios(barrios);
        
        return listBarrioResponse;
    }
}
