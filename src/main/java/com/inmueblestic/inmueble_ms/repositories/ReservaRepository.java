package com.inmueblestic.inmueble_ms.repositories;
import com.inmueblestic.inmueble_ms.models.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
//cuenta  es a inmueble lo que transaccion es a reserva

public interface ReservaRepository extends MongoRepository<Reserva, String>{
    List<Reserva> findByPropietario(String propietario);
    List<Reserva> findByInquilino(String inquilino);
    List<Reserva> findByIdInmueble(String idInmueble);

}
