package com.inmueblestic.inmueble_ms.controllers;

import com.inmueblestic.inmueble_ms.models.Inmueble;
import com.inmueblestic.inmueble_ms.models.Reserva;
import com.inmueblestic.inmueble_ms.repositories.InmuebleRepository;
import com.inmueblestic.inmueble_ms.exception.InmuebleNoFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @GetMapping("/inmueblesID/{id}")
    Inmueble getInmueblesId(@PathVariable String id){
        Inmueble inmueblesPropietario = inmuebleRepository.findById(id)
                .orElseThrow(() -> new InmuebleNoFoundException("No se encontro el inmueble"));
        return inmueblesPropietario;
    }
    //obtenerInmuebleDisponiblePorCiudad
    @GetMapping("/inmueblesCiudad/{ubicacionCiudad}")
    List <Inmueble> getInmueblesCiudad(@PathVariable String ubicacionCiudad){
        List <Inmueble> inmueblesCiudad = inmuebleRepository.findPropertyByUbicacionCiudad(ubicacionCiudad);
        if(inmueblesCiudad.size()==0){
            throw new InmuebleNoFoundException("No se encontraron imuebles asociados a la ciudad "+ubicacionCiudad);
        }
        return inmueblesCiudad;
    }

    //obtenerInmueblesPorPropietario
    @GetMapping("/misEspacios/{propietario}")
    List <Inmueble> getInmuebles(@PathVariable String propietario){
        List <Inmueble> inmueblesPropietario = inmuebleRepository.findOwnerPropertyByPropietario(propietario);
        if(inmueblesPropietario.size()==0){
            throw new InmuebleNoFoundException("No se encontraron imuebles asociados al usuario "+propietario);
        }

        return inmueblesPropietario;
    }
    //Obtener todos los inmueblesDisponibles
    @GetMapping("/inmuebles")
    List <Inmueble> getinmuebles(){
        List <Inmueble> inmuebles = inmuebleRepository.findAll();
        if(inmuebles.size()==0){
            throw new InmuebleNoFoundException("No se encontraron imuebles");
        }
        return inmuebles;
    }

//crear inmueble//
    @PostMapping("/publicarEspacio")
    Inmueble newInmueble(@RequestBody Inmueble inmueble){
        //Se debe hacer logica de que el nmueble debe pertenecer al usuario logueado
        return inmuebleRepository.save(inmueble);
    }
    @PutMapping("/inmueble/update")
    Inmueble updateInmueble(@RequestBody Inmueble inmueble){
        Inmueble inmuebleUpdate = inmuebleRepository.findById(inmueble.getId()).orElseThrow(
                () -> new InmuebleNoFoundException("No se encontro el inmueble"));
        inmuebleUpdate.setUbicacionCiudad(inmueble.getUbicacionCiudad());
        inmuebleUpdate.setUbicacionBarrio(inmueble.getUbicacionBarrio());
        inmuebleUpdate.setHabitaciones(inmueble.getHabitaciones());
        inmuebleUpdate.setNumeroBanios(inmueble.getNumeroBanios());
        inmuebleUpdate.setDimension(inmueble.getDimension());
        inmuebleUpdate.setTipoInmueble(inmueble.getTipoInmueble());
        inmuebleUpdate.setDescripcion(inmueble.getDescripcion());
        inmuebleUpdate.setPrecioDia(inmueble.getPrecioDia());
        return inmuebleRepository.save(inmuebleUpdate);
    }
    @DeleteMapping("/inmueble/delete/{inmuebleId}")
        String deleteInmueble (@PathVariable String inmuebleId){
        Inmueble inmueble = inmuebleRepository.findById(inmuebleId).orElseThrow(() -> new InmuebleNoFoundException("No se encontro el inmueble"));
        inmuebleRepository.deleteById(inmuebleId);
        return "Borrado Exitoso";
    }

}
