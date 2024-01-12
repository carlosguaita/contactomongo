package com.contactos.services;

import com.contactos.models.Contacto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactoRepository extends MongoRepository<Contacto,String> {

    public Contacto findByNombre(String nombre);
    public Contacto findByCedula(String cedula);

}
