package com.appproveedoresservicios.excepciones;

import java.sql.Timestamp;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    //ENCONTRO LA LISTA, PERO ESTA VACÍA 200->404
    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException e) {
        ErrorResponse error = buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        
        return new ResponseEntity(error, HttpStatus.OK);
    }
    
    //NO ENCONTRO LO QUE BUSCABA 404
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        ErrorResponse error = buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    /*500*/
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        ErrorResponse error = buildErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     //500
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(Exception e) {
        ErrorResponse error = buildErrorResponse("{Message: }" + e.getMessage() + "{StackTrace: }" + e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //SALTA CUANDO LOS CAMPOS NO SON VALIDOS
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse error = new ErrorResponse();
        
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        
        for (FieldError fieldError : e.getFieldErrors()) {
            error.add(fieldError.getDefaultMessage());
        }
        
        error.setTimestamp(Timestamp.from(Instant.now()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    //EL CORREO YA ESTÁ EN USO
    @ExceptionHandler(value = EmailAlreadyInUseException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyInUseException(EmailAlreadyInUseException e) {
        ErrorResponse error = buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    //EL JWT NO SE PUDO VALIDAR
    @ExceptionHandler(value = ServicioAppException.class)
    public ResponseEntity<ErrorResponse> handleServicioAppException(ServicioAppException e) {
        ErrorResponse error = buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //CONSTRUYE EL ERROR A DEVOLVER
    private ErrorResponse buildErrorResponse(String message, HttpStatus httpStatus) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(httpStatus.value());
        errorResponse.setTimestamp(Timestamp.from(Instant.now()));
        errorResponse.add(message);

        return errorResponse;
    }
}
