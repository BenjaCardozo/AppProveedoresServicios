/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appproveedoresservicios.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Mega Tecnologia
 */
public class ClienteRequest {
    private String nombre;
    private String correo;
    private String clave;
    private String clave2;
    private MultipartFile foto;
    private String contacto;
    private String barrio;
}
