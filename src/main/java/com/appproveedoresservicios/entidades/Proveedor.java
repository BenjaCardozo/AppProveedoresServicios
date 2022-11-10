package com.appproveedoresservicios.entidades;

import java.util.List;
import javax.persistence.Entity;

public class Proveedor extends Persona{
    
    protected String contacto;
    protected String descripcion;
    protected String rubro;
    protected double promedioFeedback;
    protected String disponibilidad;
    protected List<Trabajo> trabajo;
    protected List<FeedBack> feedback;

    public Proveedor() {
    }



    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public double getPromedioFeedback() {
        return promedioFeedback;
    }

    public void setPromedioFeedback(double promedioFeedback) {
        this.promedioFeedback = promedioFeedback;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public List<Trabajo> getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(List<Trabajo> trabajo) {
        this.trabajo = trabajo;
    }

    public List<FeedBack> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<FeedBack> feedback) {
        this.feedback = feedback;
    }
    
    
    
}
