package com.alkemy.CHALLENGE.BACKEND.services.implement;

import com.alkemy.CHALLENGE.BACKEND.models.PersonajePelicula;
import com.alkemy.CHALLENGE.BACKEND.repositories.PersonajePeliculaRepository;
import com.alkemy.CHALLENGE.BACKEND.services.PersonajePeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajePeliculaServiceImplement implements PersonajePeliculaService {

    @Autowired
    private PersonajePeliculaRepository personajePeliculaRepository;


    @Override
    public void guardarPersonajePelicula(PersonajePelicula personajePelicula) {
        personajePeliculaRepository.save(personajePelicula);
    }

    @Override
    public void eliminarPersonajePelicula(PersonajePelicula personajePelicula) {
        personajePeliculaRepository.delete(personajePelicula);

    }
}
