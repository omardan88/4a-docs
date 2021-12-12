package com.inmueblestic.inmueble_ms.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@ResponseBody
public class InmbuebleNotAvailableAdvice {
    @ResponseBody
    @ExceptionHandler(InmuebleNotAvailableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String EntityNotFoundAdvice(InmuebleNotAvailableException ex){
        return ex.getMessage();
    }
}
