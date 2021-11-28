package com.inmueblestic.inmueble_ms.models;

import org.springframework.data.annotation.Id;

public class Inmueble {
    @Id
    private String id;
    private String propietario;
    private String Ubicacion_ciudad;
    private String Ubicacion_barrio;
    private Integer habitaciones;
    private Integer baños;
    private double dimension;
    private String descripcion;
    private double precio;
    private boolean disponible;

    public Inmueble(String id, String propietario, String ubicacion_ciudad, String ubicacion_barrio,
                    Integer habitaciones, Integer  banos, double dimension, String descripcion, double precio, boolean disponible) {

        this.id = id;
        this.propietario = propietario;
        Ubicacion_ciudad = ubicacion_ciudad;
        Ubicacion_barrio = ubicacion_barrio;
        this.habitaciones = habitaciones;
        this.baños = baños;
        this.dimension = dimension;
        this.descripcion= descripcion;
        this.precio = precio;
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

    public String getUbicacion_ciudad() {
        return Ubicacion_ciudad;
    }

    public void setUbicacion_ciudad(String ubicacion_ciudad) {
        Ubicacion_ciudad = ubicacion_ciudad;
    }

    public String getUbicacion_barrio() {
        return Ubicacion_barrio;
    }

    public void setUbicacion_barrio(String ubicacion_barrio) {
        Ubicacion_barrio = ubicacion_barrio;
    }

    public Integer getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Integer habitaciones) {
        this.habitaciones = habitaciones;
    }

    public Integer getBaños() {
        return baños;
    }

    public void setBaños(Integer baños) {
        this.baños = baños;
    }

    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
}
