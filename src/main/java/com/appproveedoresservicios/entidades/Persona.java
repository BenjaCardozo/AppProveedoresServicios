package com.appproveedoresservicios.entidades;

import com.appproveedoresservicios.enums.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public abstract class Persona {   
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy ="uuid2")
    protected Long id;
    
    protected String nombre;
    protected String correo;
    protected String clave;
    protected String barrio;
    
    @OneToOne
    protected Foto foto;
    protected Boolean alta;
    
    @Enumerated(EnumType.STRING)
    protected Rol rol;
}
