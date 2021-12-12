package com.inmueblestic.inmueble_ms.repositories;
import com.inmueblestic.inmueble_ms.models.Inmueble;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//esto es controlador
public interface InmuebleRepository extends MongoRepository<Inmueble, String>{
    List<Inmueble> findOwnerPropertyByPropietario (String propietario);
    List<Inmueble> findPropertyByPrecioDia (double precioDia); //PDT IMPLEMENTAR, BUSCAR INMUEBLES POR MENOR PRECIO
    List<Inmueble> findPropertyByUbicacionCiudad (java.lang.String ubicacionCiudad); //PDT IMPLEMENTAR, BUSCAR INMUEBLES POR CIUDAD
    List<Inmueble> findByDisponible(boolean disponible);
}
