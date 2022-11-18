package com.appproveedoresservicios.entidades;

import com.appproveedoresservicios.enums.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import lombok.Data;

//@MappedSuperclass
@Entity
@Data
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    protected String nombre;
    protected String correo;
    protected String clave;

    @OneToOne
    protected Foto foto;
    protected Boolean alta;

    @Enumerated(EnumType.STRING)
    protected Rol rol;
}
