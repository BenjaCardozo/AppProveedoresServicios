package com.appproveedoresservicios.entidades;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
    @OneToMany(cascade = {CascadeType.REMOVE}, orphanRemoval = true, mappedBy = "proveedor")
    private List<Trabajo> trabajo;
    @OneToMany//(cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    private List<FeedBack> feedback;

}
