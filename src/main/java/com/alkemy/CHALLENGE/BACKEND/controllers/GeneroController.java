package com.alkemy.CHALLENGE.BACKEND.controllers;

import com.alkemy.CHALLENGE.BACKEND.models.Genero;
import com.alkemy.CHALLENGE.BACKEND.services.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneroController {
    @Autowired
    private GeneroService generoService;

    @PostMapping("genero")
    public ResponseEntity<Object> crearGenero (@RequestParam String nombre, @RequestParam String imagen){

        Genero nuevoGenero = new Genero(nombre, imagen);
        generoService.guardarGenero(nuevoGenero);

        return new ResponseEntity<>("Genero creado", HttpStatus.CREATED);
    }
}
