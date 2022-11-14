package com.appproveedoresservicios.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Proveedor extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String contacto;
    private String descripcion;
    private String rubro;
    private Double promedioFeedback;
    private String disponibilidad;
    @OneToMany
    private List<Trabajo> trabajo;
    @OneToMany
    private List<FeedBack> feedback;

}
