package com.inmueblestic.inmueble_ms.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@ResponseBody
public class InmuebleNoFoundAdvice {

    @ResponseBody
    @ExceptionHandler(InmuebleNoFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String EntityNotFoundAdvice(InmuebleNoFoundException ex){
        return ex.getMessage();
    }
}
