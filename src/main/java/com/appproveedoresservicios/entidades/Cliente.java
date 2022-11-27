package com.appproveedoresservicios.entidades;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Cliente extends Usuario {

    private String barrio;
    private String contacto;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Trabajo> trabajos;
}
