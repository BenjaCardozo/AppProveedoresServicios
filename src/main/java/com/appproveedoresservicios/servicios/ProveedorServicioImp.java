
package com.appproveedoresservicios.servicios;

import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.excepciones.AppExcepcion;
import com.appproveedoresservicios.repositorios.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorServicioImp implements ProveedorServicio{
    
    @Autowired
    ProveedorRepositorio proveedorRepositorio;
    

    @Override
    public void crearProveedor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificarProveedor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarProveedor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void darBajaProveedor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void darAltaProveedor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Proveedor buscarProveedor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    private void validar(String correo, String nombre, String clave, String clave2, String barrio, String contacto) throws AppExcepcion{
        
        if (correo.isEmpty() || correo == null) {
            throw new AppExcepcion("El correo no puede estar vacío.");
        }
        
        if (nombre.isEmpty() || nombre == null) {
            throw new AppExcepcion("El nombre no puede estar vacío.");
        }
        
        if (clave.length() < 8 || clave.length() > 16) {
            throw new AppExcepcion("La clave tiene que ser de un mínimo de 8 caracteres y un máximo de 16 caracteres.");
        }
        
        if (!clave.equals(clave2)){
            throw new AppExcepcion("Las claves deben ser iguales.");
        }
        
        if (barrio.isEmpty() || barrio == null) {
            throw new AppExcepcion("El barrio no puede ser vacío.");
        }
        
        if (contacto.isEmpty() || contacto == null) {
            throw new AppExcepcion("El contacto no puede ser vacío.");
        }
        
        if (contacto.isEmpty() || contacto == null) {
            throw new AppExcepcion("El contacto no puede ser vacío.");
        }
        
    }
    
}
