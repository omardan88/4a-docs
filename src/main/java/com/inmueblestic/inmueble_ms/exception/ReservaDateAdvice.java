package com.inmueblestic.inmueble_ms.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@ResponseBody
public class ReservaDateAdvice {
    @ResponseBody
    @ExceptionHandler(ReservaDateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String EntityNotFoundAdvice(ReservaDateException ex){
        return ex.getMessage();
    }
}
