package com.appproveedoresservicios.entidades;

import com.appproveedoresservicios.enums.Rol;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import lombok.Data;

@Data
public abstract class Persona {
    
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
