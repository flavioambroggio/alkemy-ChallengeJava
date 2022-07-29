package com.alkemy.CHALLENGE.BACKEND.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String imagen;
    private String titulo;
    private LocalDate fechaDeCreacion;
    private Integer calificacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="genero_id")
    private Genero genero;

    @OneToMany(mappedBy = "pelicula", fetch = FetchType.EAGER)
    private Set<PersonajePelicula> personajePeliculas = new HashSet<>();


    public Pelicula() { }

    public Pelicula(String imagen, String titulo, LocalDate fechaDeCreacion, Integer calificacion, Genero genero) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.fechaDeCreacion = fechaDeCreacion;
        this.calificacion = calificacion;
        this.genero = genero;
    }

    public long getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    public Set<PersonajePelicula> getPersonajePeliculas() {
        return personajePeliculas;
    }

    public void setPersonajePeliculas(Set<PersonajePelicula> personajePeliculas) {
        this.personajePeliculas = personajePeliculas;
    }

}
