package com.appproveedoresservicios.entidades;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity

public class Trabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private Proveedor proveedor;
    
    private Cliente cliente; 
    
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaInicio;
    
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaFin;
    
    private boolean alta; 
    
}
