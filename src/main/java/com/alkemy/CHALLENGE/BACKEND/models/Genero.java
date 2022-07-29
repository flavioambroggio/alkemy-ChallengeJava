package com.alkemy.CHALLENGE.BACKEND.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String nombre;
    private String imagen;
    @OneToMany(mappedBy="genero", fetch=FetchType.EAGER)
    private Set<Pelicula> peliculas = new HashSet<>();

    public Genero() { }

    public Genero(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public void addPelicula(Pelicula pelicula){
        pelicula.setGenero(this);
        peliculas.add(pelicula);
    }
}
