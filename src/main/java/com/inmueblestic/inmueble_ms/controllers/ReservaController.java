package com.inmueblestic.inmueble_ms.controllers;

import com.inmueblestic.inmueble_ms.exception.InmuebleNoFoundException;
import com.inmueblestic.inmueble_ms.exception.InmuebleNotAvailableException;
import com.inmueblestic.inmueble_ms.exception.ReservaDateException;
import com.inmueblestic.inmueble_ms.exception.ReservaNotFoundException;
import com.inmueblestic.inmueble_ms.models.Reserva;
import com.inmueblestic.inmueble_ms.repositories.ReservaRepository;

import com.inmueblestic.inmueble_ms.models.Inmueble;
import com.inmueblestic.inmueble_ms.repositories.InmuebleRepository;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
        if (!reserva.getFechaFin().isAfter(reserva.getFechaInicio()) || reserva.getFechaInicio().isBefore(LocalDate.now())) {
            throw new ReservaDateException("La fecha de inicio no puede ser superior a la fecha fin de reserva o a la actual");
        }
        if (verifyInmuebleDisponible(inmuebleAlquilado, reserva.getFechaInicio(), reserva.getFechaFin())) {
            throw new ReservaDateException("El inmueble no se encuentra disponible para ese rango de fechas.");
        }
        long diasAlquiler = reserva.getFechaInicio().until(reserva.getFechaFin(), ChronoUnit.DAYS);
        reserva.setIdInmueble(inmuebleAlquilado.getId()); //VALIDAR SI ESTE METODO ES UTIL
        reserva.setPrecioTotal(inmuebleAlquilado.getPrecioDia() * diasAlquiler); //Ajusta el pecio de alquiler
        reserva.setPropietario(inmuebleAlquilado.getPropietario()); //Obtiene el dueño del inmueble,
        reserva.setFechaReserva(LocalDateTime.now());
        inmuebleAlquilado.setDisponible(false);
        inmuebleRepository.save(inmuebleAlquilado);
        return reservaRepository.save(reserva);
    }

    //mis alquileres, obtener todas las reservas bajo un Inquilino
    @GetMapping("/misBalances/{propietario}")
    List<Reserva> getReservasPropietario(@PathVariable String propietario) {
        List<Reserva> reservasUsuario = reservaRepository.findByPropietario(propietario);
        if (reservasUsuario.size() == 0) {
            throw new ReservaNotFoundException("No se encontro reservas asociadas a " + propietario);
        }
        return reservasUsuario;
    }

    @GetMapping("/misReservas/{inquilino}")
    List<Reserva> getReservasInquilino(@PathVariable String inquilino) {
        List<Reserva> reservasUsuario = reservaRepository.findByInquilino(inquilino);
        if (reservasUsuario.size() == 0) {
            throw new ReservaNotFoundException("No se econtro reservas asociadas a " + inquilino);
        }
        return reservasUsuario;
    }
    @GetMapping("/reserva/{id}")
    Reserva getReservasId(@PathVariable String id){
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException("No se encontro la reserva"));
        return reserva;
    }

    @PutMapping("/misReservas/update")
    //METODO UPDATE, PUEDE SOBREESCRIBR FECHAS YA AGENDADAS, SE DEBE VALIDAR EN UN FUTURO
    Reserva updateReserva(@RequestBody Reserva reserva) {
        Reserva reservaUpdate = reservaRepository.findById(reserva.getId()).orElseThrow(
                () -> new ReservaNotFoundException("No se encontro una reserva asociada")
        );

        Inmueble inmuebleAlquilado = inmuebleRepository.findById(reservaUpdate.getIdInmueble()).orElse(null);
        if (inmuebleAlquilado == null) {
            throw new InmuebleNoFoundException("No se encontro el inmueble indicado");
        }
        //calcular precio de alquier
        if (reserva.getFechaFin().isBefore(reserva.getFechaInicio()) || reserva.getFechaInicio().isBefore(LocalDate.now())) {
            throw new ReservaDateException("La fecha de inicio no puede ser superior a la fecha fin de reserva o a la actual");
        }
        reservaUpdate.setFechaInicio(reserva.getFechaInicio());
        reservaUpdate.setFechaFin(reserva.getFechaFin());
        long diasAlquiler = reserva.getFechaInicio().until(reserva.getFechaFin(), ChronoUnit.DAYS);
        reserva.setPrecioTotal(inmuebleAlquilado.getPrecioDia() * diasAlquiler);
        reservaUpdate.setFechaReserva(LocalDateTime.now());
        return reservaRepository.save(reservaUpdate);
    }

    @DeleteMapping("/misReservas/delete/{reservaId}")
    String deleteReserva(@PathVariable String reservaId) {
        Reserva deleteReserva = reservaRepository.findById(reservaId).orElseThrow(
                () -> new ReservaNotFoundException("No se encontro una reserva asociada")
        );
        Inmueble inmuebleAlquilado = inmuebleRepository.findById(deleteReserva.getIdInmueble()).orElse(null);
        if (inmuebleAlquilado == null) {
            throw new InmuebleNoFoundException("No se encontro el inmueble asociado a la reserva");
        }
        inmuebleAlquilado.setDisponible(true);
        inmuebleRepository.save(inmuebleAlquilado);
        reservaRepository.deleteById(reservaId);
        return "Borrado Exitoso";
    }

    ///si la reserva asociadoa al inmueble con status disponible false,tiene fecha fin anterior a fecha actual, entonces
    ///actualizar el estatus del inmueble a false.

    private boolean verifyInmuebleDisponible(Inmueble inmueble, LocalDate fechaInicio, LocalDate fechaFin) {
        boolean fechaReservaYaRegistrada = false;
        if (inmueble == null) {
            throw new InmuebleNoFoundException("No se encontro el inmueble indicado");
        }
        if (!inmueble.isDisponible()) {
            List<Reserva> reservas = reservaRepository.findByIdInmueble(inmueble.getId());
            if (reservas.size() == 0) {
                throw new ReservaNotFoundException("No se encontro una reserva asociada");
            }
            for (Reserva reserva : reservas) {
                if ((!fechaInicio.isBefore(reserva.getFechaInicio()) && !fechaInicio.isAfter(reserva.getFechaFin()))
                    || ((!fechaFin.isBefore(reserva.getFechaInicio()) && !fechaFin.isAfter(reserva.getFechaFin())))    ) {
                        fechaReservaYaRegistrada = true;
                }
            }
        }
        return fechaReservaYaRegistrada;
    }


}
