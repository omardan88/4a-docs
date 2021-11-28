package com.inmueblestic.inmueble_ms.controllers;

import com.inmueblestic.inmueble_ms.models.Inmueble;
import com.inmueblestic.inmueble_ms.repositories.InmuebleRepository;
import com.inmueblestic.inmueble_ms.exception.InmuebleNoFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class InmuebleController {
    private final InmuebleRepository inmuebleRepository;

    public InmuebleController(InmuebleRepository inmuebleRepository)
    {
        this.inmuebleRepository = inmuebleRepository; //inyeccion de dependencias, el repositorio lo asigna y queda conectado a la DB para operar
    }


/*** obetener inmubles creados por un usuario ****/
    @GetMapping("/inmuebles/{id}")
    Inmueble getInmueble(@PathVariable String id){
        return inmuebleRepository.findById(id).orElseThrow(() -> new InmuebleNoFoundException("No Se encontro inmuebles para este Propietario " + id)
        );
    }
/**********  crear inmueble       *******************/
    @PostMapping("/inmuebles")
    Inmueble newInmueble(@RequestBody Inmueble inmueble){
        return inmuebleRepository.save(inmueble);
    }
}
