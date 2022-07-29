package com.alkemy.CHALLENGE.BACKEND.services.implement;

import com.alkemy.CHALLENGE.BACKEND.dtos.PersonajeDTO;
import com.alkemy.CHALLENGE.BACKEND.models.Personaje;
import com.alkemy.CHALLENGE.BACKEND.repositories.PersonajeRepository;
import com.alkemy.CHALLENGE.BACKEND.services.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonajeServiceImplement implements PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;


    @Override
    public Personaje traerPersonaje(Long id) {
        return personajeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Personaje> traerPersonajes() {
        return personajeRepository.findAll();
    }

    @Override
    public List<Personaje> traerPersonajesPorId(List<Long> ids) {
        return personajeRepository.findAllById(ids);
    }


    @Override
    public List<PersonajeDTO> traerPersonajesDTO() {
        return personajeRepository.findAll().stream().map(PersonajeDTO::new).collect(Collectors.toList());
    }

    @Override
    public void guardarPersonaje(Personaje personaje) {
        personajeRepository.save(personaje);

    }

    @Override
    public void eliminarPersonaje(Personaje personaje) {
        personajeRepository.delete(personaje);
    }
}
