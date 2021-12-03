package com.inmueblestic.inmueble_ms.controllers;

import com.inmueblestic.inmueble_ms.exception.InmuebleNoFoundException;
import com.inmueblestic.inmueble_ms.exception.InmuebleNotAvailableException;
import com.inmueblestic.inmueble_ms.models.Reserva;
import com.inmueblestic.inmueble_ms.repositories.ReservaRepository;

import com.inmueblestic.inmueble_ms.models.Inmueble;
import com.inmueblestic.inmueble_ms.repositories.InmuebleRepository;

import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class ReservaController {

    private final ReservaRepository reservaRepository;
    private final InmuebleRepository inmuebleRepository;

    public ReservaController(ReservaRepository reservaRepository, InmuebleRepository inmuebleRepository) {
        this.reservaRepository = reservaRepository;
        this.inmuebleRepository = inmuebleRepository;
    }

    @PostMapping("/reservas")
    Reserva newReserva(@RequestBody Reserva reserva) {
        Inmueble inmuebleAlquilado = inmuebleRepository.findById(reserva.getIdInmueble()).orElse(null);
        if (inmuebleAlquilado == null) {
            throw new InmuebleNoFoundException("No se encontro el inmueble indicado");
        }
        if(!inmuebleAlquilado.isDisponible()){
            throw new InmuebleNotAvailableException("El inmueble ya se encuentra rentado");
        }
        //calcular precio de alquier
        long diasAlquiler = reserva.getFechaInicio().until(reserva.getFechaFin(), ChronoUnit.DAYS);
        reserva.setIdInmueble(inmuebleAlquilado.getId()); //VALIDAR SI ESTE METODO ES UTIL
        reserva.setPrecioTotal(inmuebleAlquilado.getPrecioDia() * diasAlquiler); //Ajusta el pecio de alquiler
        reserva.setPropietario(inmuebleAlquilado.getPropietario()); //Obtiene el dueño del inmueble,
        reserva.setFechaReserva(LocalDateTime.now());

        inmuebleAlquilado.setDisponible(false);
        inmuebleRepository.save(inmuebleAlquilado);
        return reservaRepository.save(reserva);
    }

    @GetMapping("/reservas/{inquilino}")
    List<Reserva> getReservas(@PathVariable String inquilino) {
        List<Reserva> reservasUsuario= reservaRepository.findByInquilino(inquilino);
     return reservasUsuario;
    }
}

    /***
     *
     * @param reserva
     * @return

    @PostMapping("/reservas")
    Reserva newReserva(@RequestBody Reserva reserva){
           //proceso reserva, 1. usuario selecciona un inmueble de los disponibles (se vera en el front bajo el query findByDisponible
            2) al hacer clic el reservar, hace un query en donde recupera el inmueble getInmueblesId
            3)

            Cambiar estado del inmueble de disponible true a false



        Inmueble  cuentaPropietario = inmuebleRepository.findOwnerPropertyByPropietario(reserva.getPropietario()).orElse( null);
        Inmueble  cuentaInquilino = inmuebleRepository.findBy(reserva.getInquilino()).orElse(null);

        if(cuentaPropietario == null) {
            throw new InmuebleNoFoundException("No se encontro el usuario solicitado" + reserva.getPropietario());
        }
        if(cuentaInquilino == null) {
            throw new InmuebleNoFoundException("No se encontro el usuario solicitado" + reserva.getInquilino());
        }


         return null;

    }
     aqui vamos escucho ideas de esa logica para asignar el inmueble*/
