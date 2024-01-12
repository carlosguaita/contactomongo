package com.contactos.services;


import com.contactos.models.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactoService {


    private ContactoRepository contactoRepository;

    public ContactoService(ContactoRepository contactoRepository){
        this.contactoRepository=contactoRepository;
    }

    public List<Contacto> listaContactos(){
        List<Contacto> listaContactos = new ArrayList<>();
        try{
            listaContactos = contactoRepository.findAll();
        }catch (Exception ex){
            System.out.println("No se pudo conectar con la base de datos");
        }
        return listaContactos;
    }

    public Contacto obtenerPorCedula(String cedula){
        Contacto contacto = null;
        try {
            contacto=contactoRepository.findByCedula(cedula);
        }catch (Exception ex){
            System.out.println("No se encontro un contacto con esa cedula");
        }
        return contacto;
    }

    public void agregarContacto(Contacto contacto){
        try{
            contactoRepository.save(contacto);
        }catch (Exception ex){
            System.out.println("No se puede guardar el contacto");
        }
    }

    public void borrarContacto(String cedula){
        try{
            Contacto contacto = contactoRepository.findByCedula(cedula);
            contactoRepository.delete(contacto);
        }catch (Exception ex){
            System.out.println("No se puede encontrar el contacto a borrar");
        }
    }

    public void editarContacto(String cedula, Contacto contacto){
        try{
            Contacto contactoEditar = contactoRepository.findByCedula(cedula);
            if(contactoEditar!=null){
                contactoEditar.setNombre(contacto.getNombre());
                contactoEditar.setTelefono(contacto.getTelefono());
                contactoEditar.setCorreo(contacto.getCorreo());
                contactoRepository.save(contactoEditar);
            }
        }catch (Exception ex){
            System.out.println("No se puede encontrar el contacto a ser editado");
        }
    }


}
