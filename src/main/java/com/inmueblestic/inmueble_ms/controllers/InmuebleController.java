package com.inmueblestic.inmueble_ms.controllers;

import com.inmueblestic.inmueble_ms.models.Inmueble;
import com.inmueblestic.inmueble_ms.repositories.InmuebleRepository;
import com.inmueblestic.inmueble_ms.exception.InmuebleNoFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Crear y consultar un Inmueble
@RestController
public class InmuebleController {
    private final InmuebleRepository inmuebleRepository;

    public InmuebleController(InmuebleRepository inmuebleRepository)
    {
        this.inmuebleRepository = inmuebleRepository; //inyeccion de dependencias, el repositorio lo asigna y queda conectado a la DB para operar
    }
    //obtenerInmuebleDisponiblePorID
    @GetMapping("/inmuebles/{id}")
    Inmueble getInmueblesId(@PathVariable String id){
        Inmueble inmueblesPropietario = inmuebleRepository.findById(id)
                .orElseThrow(() -> new InmuebleNoFoundException("No se encontro inmueble"));
        return inmueblesPropietario;
    }

    //obtenerInmueblesPorPropietario
    @GetMapping("/inmueblesPropietario/{propietario}")
    List <Inmueble> getInmuebles(@PathVariable String propietario){
        List <Inmueble> inmueblesPropietario = inmuebleRepository.findOwnerPropertyByPropietario(propietario);
        return inmueblesPropietario;
    }
    //Obtener todos los inmueblesDisponibles
    @GetMapping("/inmueblesDisponibles")
    List <Inmueble> getInmueblesDisponibles(){
        List <Inmueble> inmueblesDisponibles = inmuebleRepository.findByDisponible(true);
        return inmueblesDisponibles;
    }



//crear inmueble//
    @PostMapping("/inmuebles")
    Inmueble newInmueble(@RequestBody Inmueble inmueble){
        //Se debe hacer logica de que el nmueble debe pertenecer al usuario logueado
        return inmuebleRepository.save(inmueble);
    }
}
