package com.inmueblestic.inmueble_ms.repositories;
import com.inmueblestic.inmueble_ms.models.Inmueble;
import org.springframework.data.mongodb.repository.MongoRepository;

//esto es controlador
public interface InmuebleRepository extends MongoRepository<Inmueble, String>{
}
