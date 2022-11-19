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
public class InvalidCredentialsException extends Exception {

    /**
     * Creates a new instance of <code>InvalidCredentialsException</code>
     * without detail message.
     */
    public InvalidCredentialsException() {
    }

    /**
     * Constructs an instance of <code>InvalidCredentialsException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidCredentialsException(String msg) {
        super(msg);
    }
}
