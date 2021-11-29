package com.inmueblestic.inmueble_ms.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Reserva {
    @Id
    private String id;
    private String propietario;
    private String inquilino;
    private Date fechaInicio;

    public Reserva(String id, String propietario, String inquilino, Date fechaInicio) {
        this.id = id;
        this.propietario = propietario;
        this.inquilino = inquilino;
        this.fechaInicio = fechaInicio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
