package com.inmueblestic.inmueble_ms.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@ResponseBody
public class ReservaNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ReservaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String EntityNotFoundAdvice(ReservaNotFoundException ex){
        return ex.getMessage();
    }
}

