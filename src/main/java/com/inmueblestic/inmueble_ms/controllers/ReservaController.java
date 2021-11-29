package com.inmueblestic.inmueble_ms.controllers;

import com.inmueblestic.inmueble_ms.models.Reserva;
import com.inmueblestic.inmueble_ms.repositories.ReservaRepository;


import com.inmueblestic.inmueble_ms.models.Inmueble;
import com.inmueblestic.inmueble_ms.repositories.InmuebleRepository;
import com.inmueblestic.inmueble_ms.exception.InmuebleNoFoundException;

import org.springframework.web.bind.annotation.*;

@RestController
public class ReservaController {
    private final ReservaRepository reservaRepository;
    private final InmuebleRepository inmuebleRepository;

       public ReservaController(ReservaRepository reservaRepository, InmuebleRepository inmuebleRepository) {
        this.reservaRepository = reservaRepository;
        this.inmuebleRepository = inmuebleRepository;
    }

    @PostMapping("/reservas")
    Reserva newReserva(@RequestBody Reserva reserva){
        Inmueble  cuentaPropietario = inmuebleRepository.findById(reserva.getPropietario()).orElse( null);
        Inmueble  cuentaInquilino = inmuebleRepository.findById(reserva.getInquilino()).orElse(null);

        if(cuentaPropietario == null) {
            throw new InmuebleNoFoundException("No se encontro el usuario solicitado" + reserva.getPropietario());
        }
        if(cuentaInquilino == null) {
            throw new InmuebleNoFoundException("No se encontro el usuario solicitado" + reserva.getInquilino());
        }

        /**** aqui vamos escucho ideas de esa logica para asignar el inmueble****/
         return null;
    }

}