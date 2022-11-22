package com.appproveedoresservicios.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Cliente extends Usuario {

    private String barrio;
    private String contacto;

    @OneToMany
    private List<Trabajo> trabajos;
}
