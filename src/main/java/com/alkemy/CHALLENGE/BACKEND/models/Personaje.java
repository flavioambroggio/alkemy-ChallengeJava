package com.alkemy.CHALLENGE.BACKEND.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private String peso;
    private String historia;
    @OneToMany(mappedBy = "personaje", fetch = FetchType.EAGER)
    private Set<PersonajePelicula> personajePeliculas = new HashSet<>();

    public Personaje() { }
    public Personaje(String imagen, String nombre, Integer edad, String peso, String historia) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public Set<PersonajePelicula> getPersonajePeliculas() {
        return personajePeliculas;
    }

    public void setPersonajePeliculas(Set<PersonajePelicula> personajePeliculas) {
        this.personajePeliculas = personajePeliculas;
    }

    public void addPersonajePelicula(PersonajePelicula personajePelicula){
        personajePelicula.setPersonaje(this);
        personajePeliculas.add(personajePelicula);
    }
}
