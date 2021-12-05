package com.inmueblestic.inmueble_ms.models;

import org.springframework.data.annotation.Id;

public class Inmueble {
    @Id
    private String id;
    private String propietario;
    private String ubicacionCiudad;
    private String ubicacionBarrio;
    private Integer habitaciones;
    private Integer numeroBanios;
    private double dimension; //se refiere a los m2
    private String tipoInmueble; //Desde el front solo se desplegaran 3 opciones, casa, apto, habitacion
    private String descripcion;
    private double precioDia;
    private boolean disponible;




    public Inmueble(String id, String propietario, String ubicacionCiudad, String ubicacionBarrio,
                  Integer habitaciones, Integer  numeroBanios, double dimension, String tipoInmueble, String descripcion, double precioDia, boolean disponible) {
        this.id = id;
        this.propietario = propietario;
        this.ubicacionCiudad = ubicacionCiudad;
        this.ubicacionBarrio = ubicacionBarrio;
        this.habitaciones = habitaciones;
        this.numeroBanios = numeroBanios;
        this.dimension = dimension;
        this.descripcion= descripcion;
        this.tipoInmueble = tipoInmueble;
        this.precioDia = precioDia;
        this.disponible= disponible;
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

    public String getUbicacionCiudad() {
        return ubicacionCiudad;
    }

    public void setUbicacionCiudad(String ubicacionCiudad) {
        this.ubicacionCiudad = ubicacionCiudad;
    }

    public String getUbicacionBarrio() {
        return ubicacionBarrio;
    }

    public void setUbicacionBarrio(String ubicacionBarrio) {
        this.ubicacionBarrio = ubicacionBarrio;
    }

    public Integer getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Integer habitaciones) {
        this.habitaciones = habitaciones;
    }

    public Integer getNumeroBanios() {
        return numeroBanios;
    }

    public void setNumeroBanios(Integer numeroBanios) {
        this.numeroBanios = numeroBanios;
    }

    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    public double getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(double precioDia) {
        this.precioDia = precioDia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(String tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }
}
