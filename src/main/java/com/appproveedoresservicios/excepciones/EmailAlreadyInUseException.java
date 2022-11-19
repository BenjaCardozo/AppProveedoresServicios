/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appproveedoresservicios.excepciones;

/**
 *
 * @author Walter
 */
public class EmailAlreadyInUseException extends Exception {

    /**
     * Creates a new instance of <code>emailAlreadyInUseException</code> without
     * detail message.
     */
    public EmailAlreadyInUseException() {
    }

    /**
     * Constructs an instance of <code>emailAlreadyInUseException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public EmailAlreadyInUseException(String msg) {
        super(msg);
    }
}
