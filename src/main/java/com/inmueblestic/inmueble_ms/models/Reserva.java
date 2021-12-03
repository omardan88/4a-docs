package com.inmueblestic.inmueble_ms.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Reserva {
    @Id
    private String id;
    private String idInmueble;
    private String propietario;
    private String inquilino;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaInicio;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaFin;
    private double precioTotal;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaReserva;



    public Reserva(String id, String idInmueble, String propietario, String inquilino, String fechaInicio, String fechaFin, double precioTotal, LocalDateTime fechaReserva) {
        this.id = id;
        this.idInmueble =idInmueble;
        this.propietario = propietario;
        this.inquilino = inquilino;
        this.fechaInicio = LocalDate.parse(fechaInicio, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.fechaFin = LocalDate.parse(fechaFin, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.precioTotal = precioTotal;
        this.fechaReserva = fechaReserva;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getInquilino() {
        return inquilino;
    }

    public void setInquilino(String inquilino) {
        this.inquilino = inquilino;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
