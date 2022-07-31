package com.alkemy.CHALLENGE.BACKEND.controllers;

import com.alkemy.CHALLENGE.BACKEND.dtos.PersonajeDTO;
import com.alkemy.CHALLENGE.BACKEND.models.Pelicula;
import com.alkemy.CHALLENGE.BACKEND.models.Personaje;
import com.alkemy.CHALLENGE.BACKEND.models.PersonajePelicula;
import com.alkemy.CHALLENGE.BACKEND.models.Usuario;
import com.alkemy.CHALLENGE.BACKEND.services.PeliculaService;
import com.alkemy.CHALLENGE.BACKEND.services.PersonajePeliculaService;
import com.alkemy.CHALLENGE.BACKEND.services.PersonajeService;
import com.alkemy.CHALLENGE.BACKEND.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;
    @Autowired
    private PeliculaService peliculaService;
    @Autowired
    private PersonajePeliculaService personajePeliculaService;
    @Autowired
    private UsuarioService usuarioService;


    @GetMapping()
    public List<PersonajeDTO> traerPersonajes () {
        return personajeService.traerPersonajesDTO();
    }


    @PostMapping()
    public ResponseEntity<Object> crearPersonaje (@RequestParam String imagen, @RequestParam String nombre, @RequestParam Integer edad,
                                                  @RequestParam String peso, @RequestParam String historia, Authentication authentication){

        Usuario usuario = usuarioService.traerUsuarioAutenticado(authentication);

        if (usuario == null) {
            return new ResponseEntity<>("Usuario no autorizado", HttpStatus.FORBIDDEN);
        }

        Personaje nuevoPersonaje = new Personaje(imagen, nombre, edad, peso, historia);
        personajeService.guardarPersonaje(nuevoPersonaje);

        return new ResponseEntity<>("Personaje creado", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> modificarPersonaje (@PathVariable Long id, @RequestParam String imagen, @RequestParam String nombre, @RequestParam Integer edad,
                                                      @RequestParam String peso, @RequestParam String historia, Authentication authentication){

        Usuario usuario = usuarioService.traerUsuarioAutenticado(authentication);

        if (usuario == null) {
            return new ResponseEntity<>("Usuario no autorizado", HttpStatus.FORBIDDEN);
        }

        Personaje personaje = personajeService.traerPersonaje(id);

        personaje.setImagen(imagen);
        personaje.setNombre(nombre);
        personaje.setEdad(edad);
        personaje.setPeso(peso);
        personaje.setHistoria(historia);

        personajeService.guardarPersonaje(personaje);

        return new ResponseEntity<>("Personaje modificado", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarPersonaje (@PathVariable Long id, Authentication authentication){

        Usuario usuario = usuarioService.traerUsuarioAutenticado(authentication);

        if (usuario == null) {
            return new ResponseEntity<>("Usuario no autorizado", HttpStatus.FORBIDDEN);
        }

        Personaje personaje = personajeService.traerPersonaje(id);

        personaje.getPersonajePeliculas().forEach(personajePelicula -> personajePeliculaService.eliminarPersonajePelicula(personajePelicula));

        personajeService.eliminarPersonaje(personaje);

        return new ResponseEntity<>("Personaje eliminado", HttpStatus.ACCEPTED);
    }

    @GetMapping(params = "name")
    public List<PersonajeDTO> traerPersonajePorNombre(@RequestParam String name){
        if(name.isEmpty()){
            return personajeService.traerPersonajesDTO();
        }
        return personajeService.traerPersonajesDTO().stream().filter(personajeDTO -> personajeDTO.getNombre().equals(name)).collect(Collectors.toList());
    }

    @GetMapping(params = "age")
    public List<PersonajeDTO> traerPersonajePorEdad(@RequestParam Integer age){
        if(age == null){
            return personajeService.traerPersonajesDTO();
        }
        return personajeService.traerPersonajesDTO().stream().filter(personajeDTO -> personajeDTO.getEdad() == age).collect(Collectors.toList());
    }

    @GetMapping(params = "idMovie")
    public List<PersonajeDTO> traerPersonajePorIdMovie(@RequestParam Long idMovie){
        if(idMovie == null){
            return personajeService.traerPersonajesDTO();
        }
        Pelicula pelicula = peliculaService.traerPelicula(idMovie);
        Set<PersonajePelicula> personajePeliculaSet = pelicula.getPersonajePeliculas();
        Set<Personaje> personajeSet = personajePeliculaSet.stream().map(PersonajePelicula::getPersonaje).collect(Collectors.toSet());

        return personajeSet.stream().map(PersonajeDTO::new).collect(Collectors.toList());
    }

}
