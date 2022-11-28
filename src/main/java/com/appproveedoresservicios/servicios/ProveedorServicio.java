package com.appproveedoresservicios.servicios;


import com.appproveedoresservicios.dto.response.ListProveedorResponse;
import com.appproveedoresservicios.dto.request.ProveedorRequest;
import com.appproveedoresservicios.dto.response.ProveedorResponse;
import com.appproveedoresservicios.entidades.Proveedor;
import com.appproveedoresservicios.excepciones.EmailAlreadyInUseException;
import com.appproveedoresservicios.excepciones.ResourceNotFoundException;

public interface ProveedorServicio {

    ProveedorResponse crearProveedor(ProveedorRequest request) throws EmailAlreadyInUseException;

    ProveedorResponse modificarProveedor(ProveedorRequest request, Long id) throws Exception;

    void actualizarPromedioFeedBack(Long id) throws Exception;
    
    void eliminarFeedBacksYTrabajosDeProveedor(Long id);

    void eliminarProveedor(Long id) throws Exception;

    void darBajaProveedor(Long id) throws Exception;

    void darAltaProveedor(Long id) throws Exception;
    
    Proveedor findById(Long id) throws ResourceNotFoundException;

    ProveedorResponse findProveedorById(Long id) throws ResourceNotFoundException;

    ListProveedorResponse ordenarProveedoresPorBarrios();

    ListProveedorResponse ordenarProveedoresPorBarriosDesc();

    ListProveedorResponse ordenarProveedoresPorRubro();

    ListProveedorResponse ordenarProveedoresPorRubroDesc();

    ListProveedorResponse ordenarProveedoresPorFeedback();

    ListProveedorResponse ordenarProveedoresPorFeedbackDesc();

    ListProveedorResponse ordenarProveedoresPorNombre();

    ListProveedorResponse ordenarProveedoresPorNombreDesc();

    ListProveedorResponse buscarProveedoresPorBarrio(String barrio) throws ResourceNotFoundException;

    ListProveedorResponse buscarProveedoresPorRubro(String rubro) throws ResourceNotFoundException;

    ListProveedorResponse listarProveedores();

    double calcularFeedbackPromedio(Long id);
}
