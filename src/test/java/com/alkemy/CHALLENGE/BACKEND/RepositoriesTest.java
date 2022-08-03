package com.alkemy.CHALLENGE.BACKEND;

import com.alkemy.CHALLENGE.BACKEND.models.*;
import com.alkemy.CHALLENGE.BACKEND.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class RepositoriesTest {
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private PersonajeRepository personajeRepository;
    @Autowired
    private PersonajePeliculaRepository personajePeliculaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Test
    public void existenGeneros(){
        List<Genero> generos = generoRepository.findAll();
        assertThat(generos, is(not(empty())));
    }

    @Test
    public void existenPeliculas(){
        List<Pelicula> peliculas = peliculaRepository.findAll();
        assertThat(peliculas, is(not(empty())));
    }

    @Test
    public void existenPersonajesPeliculas(){
        List<PersonajePelicula> personajePeliculas = personajePeliculaRepository.findAll();
        assertThat(personajePeliculas, is(not(empty())));
    }

    @Test
    public void existenPersonajes(){
        List<Personaje> personajes = personajeRepository.findAll();
        assertThat(personajes, is(not(empty())));
    }

    @Test
    public void existeUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        assertThat(usuarios, is(not(empty())));
    }



}
