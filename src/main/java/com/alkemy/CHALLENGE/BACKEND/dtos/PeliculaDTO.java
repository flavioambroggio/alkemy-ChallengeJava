package com.alkemy.CHALLENGE.BACKEND.dtos;

import com.alkemy.CHALLENGE.BACKEND.models.Genero;
import com.alkemy.CHALLENGE.BACKEND.models.Pelicula;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PeliculaDTO {

    private long id;
    private String imagen;
    private String titulo;
    private LocalDate fechaDeCreacion;
    private int calificacion;
    private String genero;
    private Set<String> personajes = new HashSet<>();

    public PeliculaDTO() { }

    public PeliculaDTO(Pelicula pelicula) {
        this.id = pelicula.getId();
        this.imagen = pelicula.getImagen();
        this.titulo = pelicula.getTitulo();
        this.fechaDeCreacion = pelicula.getFechaDeCreacion();
        this.calificacion = pelicula.getCalificacion();
        this.genero = pelicula.getGenero().getNombre();
        this.personajes = pelicula.getPersonajePeliculas().stream().map(personajePelicula -> personajePelicula.getPersonaje().getNombre()).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public String getGenero() {
        return genero;
    }

    public Set<String> getPersonajes() {
        return personajes;
    }
}
