package com.alkemy.CHALLENGE.BACKEND.services.implement;

import com.alkemy.CHALLENGE.BACKEND.dtos.PeliculaDTO;
import com.alkemy.CHALLENGE.BACKEND.models.Pelicula;
import com.alkemy.CHALLENGE.BACKEND.repositories.PeliculaRepository;
import com.alkemy.CHALLENGE.BACKEND.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaServiceImplement implements PeliculaService {
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public Pelicula traerPelicula(Long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pelicula> traerPeliculas() {
        return peliculaRepository.findAll();
    }

    @Override
    public List<PeliculaDTO> traerPeliculasDTO() {
        return peliculaRepository.findAll().stream().map(PeliculaDTO::new).collect(Collectors.toList());
    }

    @Override
    public void guardarPelicula(Pelicula pelicula) {
        peliculaRepository.save(pelicula);

    }

    @Override
    public void eliminarPelicula(Pelicula pelicula) {
        peliculaRepository.delete(pelicula);
    }
}
