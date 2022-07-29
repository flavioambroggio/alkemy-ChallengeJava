package com.alkemy.CHALLENGE.BACKEND.services;

import com.alkemy.CHALLENGE.BACKEND.dtos.PeliculaDTO;
import com.alkemy.CHALLENGE.BACKEND.models.Pelicula;

import java.util.List;

public interface PeliculaService {

    Pelicula traerPelicula(Long id);

    List<Pelicula> traerPeliculas();

    List<PeliculaDTO> traerPeliculasDTO();

    void guardarPelicula(Pelicula pelicula);

    void eliminarPelicula(Pelicula pelicula);
}
