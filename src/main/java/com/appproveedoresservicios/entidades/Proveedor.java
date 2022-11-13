package com.appproveedoresservicios.entidades;

import com.appproveedoresservicios.enums.Rol;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Proveedor extends Persona{
    
    private String localidad;
    private String contacto;
    private String descripcion;
    private String rubro;
    private double promedioFeedback;
    private String disponibilidad;
    @OneToMany
    private List<Trabajo> trabajo;
    @OneToMany
    private List<FeedBack> feedback;
   
}
