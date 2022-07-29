package com.alkemy.CHALLENGE.BACKEND.services;

import com.alkemy.CHALLENGE.BACKEND.dtos.PersonajeDTO;
import com.alkemy.CHALLENGE.BACKEND.models.Personaje;

import java.util.List;

public interface PersonajeService {

    Personaje traerPersonaje(Long id);

    List<Personaje> traerPersonajes();

    List<Personaje> traerPersonajesPorId(List<Long> ids);

    List<PersonajeDTO> traerPersonajesDTO();

    void guardarPersonaje(Personaje personaje);

    void eliminarPersonaje(Personaje personaje);

}
