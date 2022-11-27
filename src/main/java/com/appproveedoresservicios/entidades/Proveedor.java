package com.appproveedoresservicios.entidades;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Proveedor extends Usuario {

    private String barrio;
    private String contacto;
    private String descripcion;
    private String rubro;
    private Double promedioFeedback;
    private String disponibilidad;
    @OneToMany(cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    private List<Trabajo> trabajo;
    @OneToMany
    private List<FeedBack> feedback;

}
