package com.inmueblestic.inmueble_ms.repositories;
import com.inmueblestic.inmueble_ms.models.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ReservaRepository extends MongoRepository<Reserva, String>{
    List<Reserva> findPropietario(String propietario);
    List<Reserva> findInquilino(String inquilino);

}
