package com.alkemy.CHALLENGE.BACKEND.services;

import com.alkemy.CHALLENGE.BACKEND.models.Genero;

public interface GeneroService {

    Genero traerGenero (Long id);
    void guardarGenero (Genero genero);
}
