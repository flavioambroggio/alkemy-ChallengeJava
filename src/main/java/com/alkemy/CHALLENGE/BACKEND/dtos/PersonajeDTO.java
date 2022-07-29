package com.alkemy.CHALLENGE.BACKEND.dtos;

import com.alkemy.CHALLENGE.BACKEND.models.Personaje;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonajeDTO {
    private long id;
    private String imagen;
    private String nombre;
    private int edad;
    private String peso;
    private String historia;
    private Set<String> peliculas = new HashSet<>();

    public PersonajeDTO() { }

    public PersonajeDTO(Personaje personaje) {
        this.id = personaje.getId();
        this.imagen = personaje.getImagen();
        this.nombre = personaje.getNombre();
        this.edad = personaje.getEdad();
        this.peso = personaje.getPeso();
        this.historia = personaje.getHistoria();
        this.peliculas = personaje.getPersonajePeliculas().stream().map(personajePelicula -> personajePelicula.getPelicula().getTitulo()).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getPeso() {
        return peso;
    }

    public String getHistoria() {
        return historia;
    }

    public Set<String> getPeliculas() {
        return peliculas;
    }
}
