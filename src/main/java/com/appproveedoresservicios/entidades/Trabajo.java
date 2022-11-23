package com.appproveedoresservicios.entidades;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
public class Trabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    private Proveedor proveedor;
    
    @OneToOne(fetch = FetchType.LAZY)
    private Cliente cliente; 
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaInicio;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaFin;
    
    private Boolean alta; 
    
}
