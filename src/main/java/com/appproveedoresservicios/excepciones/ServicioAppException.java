/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.appproveedoresservicios.excepciones;

/**
 *
 * @author Walter
 */
public class ServicioAppException extends RuntimeException{

    /**
     * Creates a new instance of <code>ServicioAppException</code> without
     * detail message.
     */
    public ServicioAppException() {
    }

    /**
     * Constructs an instance of <code>ServicioAppException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ServicioAppException(String msg) {
        super(msg);
    }
}
