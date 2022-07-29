package com.alkemy.CHALLENGE.BACKEND.services.implement;

import com.alkemy.CHALLENGE.BACKEND.models.Genero;
import com.alkemy.CHALLENGE.BACKEND.repositories.GeneroRepository;
import com.alkemy.CHALLENGE.BACKEND.services.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImplement implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public Genero traerGenero(Long id) {
        return generoRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarGenero(Genero genero) {
        generoRepository.save(genero);

    }
}
