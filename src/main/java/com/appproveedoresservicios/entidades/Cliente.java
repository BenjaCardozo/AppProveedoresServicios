package com.appproveedoresservicios.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Cliente extends Persona {
    
    private String contacto;
    
    @OneToMany
    private List<Trabajo> trabajos;
}
