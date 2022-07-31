package com.alkemy.CHALLENGE.BACKEND.controllers;

import com.alkemy.CHALLENGE.BACKEND.dtos.PeliculaDTO;
import com.alkemy.CHALLENGE.BACKEND.models.*;
import com.alkemy.CHALLENGE.BACKEND.services.*;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class PeliculaController {
    @Autowired
    private PersonajeService personajeService;
    @Autowired
    private PeliculaService peliculaService;
    @Autowired
    private PersonajePeliculaService personajePeliculaService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private UsuarioService usuarioService;


    @GetMapping()
    public List<PeliculaDTO> traerPeliculas (){
        return peliculaService.traerPeliculasDTO();
    }


    @PostMapping()
    public ResponseEntity<Object> crearPelicula (@RequestParam String imagen, @RequestParam String titulo, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDeCreacion,
                                                 @RequestParam Integer calificacion, @RequestParam Long idGenero, @RequestParam List<Long> idsPersonajes, Authentication authentication){

        Usuario usuario = usuarioService.traerUsuarioAutenticado(authentication);

        if (usuario == null) {
            return new ResponseEntity<>("Usuario no autorizado", HttpStatus.FORBIDDEN);
        }

        Genero genero = generoService.traerGenero(idGenero);
        List<Personaje> personajes = personajeService.traerPersonajesPorId(idsPersonajes);

        Pelicula nuevaPelicula = new Pelicula(imagen, titulo, fechaDeCreacion, calificacion, genero);
        peliculaService.guardarPelicula(nuevaPelicula);

        List<PersonajePelicula> combinaciones = personajes.stream().map(personaje -> new PersonajePelicula(personaje,nuevaPelicula)).collect(Collectors.toList());
        combinaciones.forEach(personajePelicula -> personajePeliculaService.guardarPersonajePelicula(personajePelicula));

        return new ResponseEntity<>("Pelicula creada", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> ModificarPelicula (@PathVariable Long id, @RequestParam String imagen, @RequestParam String titulo,
                                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDeCreacion,
                                                     @RequestParam Integer calificacion, @RequestParam Long idGenero, Authentication authentication){

        Usuario usuario = usuarioService.traerUsuarioAutenticado(authentication);

        if (usuario == null) {
            return new ResponseEntity<>("Usuario no autorizado", HttpStatus.FORBIDDEN);
        }

        Pelicula pelicula = peliculaService.traerPelicula(id);
        Genero genero = generoService.traerGenero(idGenero);

        pelicula.setImagen(imagen);
        pelicula.setTitulo(titulo);
        pelicula.setFechaDeCreacion(fechaDeCreacion);
        pelicula.setCalificacion(calificacion);
        pelicula.setGenero(genero);
        peliculaService.guardarPelicula(pelicula);

        return new ResponseEntity<>("Pelicula modificada", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarPelicula (@PathVariable Long id, Authentication authentication){

        Usuario usuario = usuarioService.traerUsuarioAutenticado(authentication);

        if (usuario == null) {
            return new ResponseEntity<>("Usuario no autorizado", HttpStatus.FORBIDDEN);
        }

        Pelicula pelicula = peliculaService.traerPelicula(id);

        pelicula.getPersonajePeliculas().forEach(personajePelicula -> personajePeliculaService.eliminarPersonajePelicula(personajePelicula));

        peliculaService.eliminarPelicula(pelicula);

        return new ResponseEntity<>("Pelicula eliminada", HttpStatus.ACCEPTED);
    }


    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Object> agregarPersonaje (@PathVariable Long idMovie, @PathVariable Long idCharacter, Authentication authentication){

        Usuario usuario = usuarioService.traerUsuarioAutenticado(authentication);

        if (usuario == null) {
            return new ResponseEntity<>("Usuario no autorizado", HttpStatus.FORBIDDEN);
        }

        Personaje personaje = personajeService.traerPersonaje(idCharacter);
        Pelicula pelicula = peliculaService.traerPelicula(idMovie);

        PersonajePelicula nuevaCombinacion = new PersonajePelicula(personaje,pelicula);
        personajePeliculaService.guardarPersonajePelicula(nuevaCombinacion);

        return new ResponseEntity<>("Personaje agregado", HttpStatus.CREATED);
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Object> eliminarPersonaje (@PathVariable Long idMovie, @PathVariable Long idCharacter, Authentication authentication){

        Usuario usuario = usuarioService.traerUsuarioAutenticado(authentication);

        if (usuario == null) {
            return new ResponseEntity<>("Usuario no autorizado", HttpStatus.FORBIDDEN);
        }

        Pelicula pelicula = peliculaService.traerPelicula(idMovie);
        if(pelicula == null){
            return new ResponseEntity<>("Pelicula no encontrada", HttpStatus.FORBIDDEN);
        }

        Set<PersonajePelicula> personajePeliculaSet = pelicula.getPersonajePeliculas();
        List<PersonajePelicula> combinaciones = personajePeliculaSet.stream().filter(personajePelicula -> personajePelicula.getPersonaje().getId() == idCharacter).collect(Collectors.toList());

        combinaciones.forEach(personajePelicula -> personajePeliculaService.eliminarPersonajePelicula(personajePelicula));

        return new ResponseEntity<>("Personaje eliminado", HttpStatus.CREATED);
    }

    @GetMapping(params = "name")
    public List<PeliculaDTO> traerPeliculaPorNombre(@RequestParam String name){
        if(name.isEmpty()){
            return peliculaService.traerPeliculasDTO();
        }
        return peliculaService.traerPeliculasDTO().stream().filter(peliculaDTO -> peliculaDTO.getTitulo().equals(name)).collect(Collectors.toList());
    }

    @GetMapping(params = "genre")
    public List<PeliculaDTO> traerPeliculaPorIdGenero(@RequestParam Long genre){
        if(genre == null){
            return peliculaService.traerPeliculasDTO();
        }
        Genero genero = generoService.traerGenero(genre);
        List<Pelicula> peliculaSet = peliculaService.traerPeliculas();
        List<Pelicula> peliculasFiltradas = peliculaSet.stream().filter(pelicula -> pelicula.getGenero().equals(genero)).collect(Collectors.toList());

        return peliculasFiltradas.stream().map(PeliculaDTO::new).collect(Collectors.toList());
    }

    @GetMapping(params = "order")
    public List<PeliculaDTO> traerPeliculaPorFecha(@RequestParam String order){
        List<PeliculaDTO> peliculasDTO = peliculaService.traerPeliculasDTO();
        if(Objects.equals(order, "ASC")){
            peliculasDTO.sort(Comparator.comparing(PeliculaDTO::getFechaDeCreacion));
            return peliculasDTO;
        }
        if(Objects.equals(order, "DESC")){
            Collections.sort(peliculasDTO, (j2,j1) -> j1.getFechaDeCreacion().compareTo(j2.getFechaDeCreacion()));
            return peliculasDTO;
        }
        return peliculaService.traerPeliculasDTO();
    }


}
