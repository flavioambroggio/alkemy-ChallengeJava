package com.alkemy.CHALLENGE.BACKEND;

import com.alkemy.CHALLENGE.BACKEND.models.*;
import com.alkemy.CHALLENGE.BACKEND.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class ChallengeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeBackendApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Bean
	public CommandLineRunner initData(PersonajeRepository personajeRepository, PeliculaRepository peliculaRepository, PersonajePeliculaRepository personajePeliculaRepository, GeneroRepository generoRepository, UsuarioRepository usuarioRepository) {
		return (args) -> {

			Usuario usuario = new Usuario("Flavio", "Ambroggio", "fla@ambro.com", passwordEncoder.encode("1234"));
			usuarioRepository.save(usuario);
			Usuario admin = new Usuario("Admin", "Admin", "admin@admin.com", passwordEncoder.encode("123admin"));
			usuarioRepository.save(admin);

			Personaje personaje1 = new Personaje("https://www.quever.news/u/fotografias/m/2022/5/24/f768x1-30159_30286_5050.jpg", "Elsa", 25, "Principal", "historia");
			personajeRepository.save(personaje1);
			Personaje personaje2 = new Personaje("imagen", "Anna", 20, "Principal", "historia");
			personajeRepository.save(personaje2);
			Personaje personaje3 = new Personaje("imagen", "Simba", 1, "Principal", "historia");
			personajeRepository.save(personaje3);

			Genero genero1 = new Genero("Fantasia", "imagen");
			generoRepository.save(genero1);
			Genero genero2 = new Genero("Romantica","imagen");
			generoRepository.save(genero2);

			Pelicula pelicula1 = new Pelicula("imagen", "Frozen", LocalDate.now().minusYears(5), 4, genero1);
			peliculaRepository.save(pelicula1);
			Pelicula pelicula2 = new Pelicula("imagen", "El Rey Leon", LocalDate.now().minusYears(15), 5, genero2);
			peliculaRepository.save(pelicula2);
			Pelicula pelicula3 = new Pelicula("imagen", "Vengadores", LocalDate.now().minusYears(4), 4, genero1);
			peliculaRepository.save(pelicula3);

			PersonajePelicula combinacion1 = new PersonajePelicula(personaje1, pelicula1);
			personajePeliculaRepository.save(combinacion1);
			PersonajePelicula combinacion2 = new PersonajePelicula(personaje2, pelicula1);
			personajePeliculaRepository.save(combinacion2);
			PersonajePelicula combinacion3 = new PersonajePelicula(personaje3, pelicula2);
			personajePeliculaRepository.save(combinacion3);

		};
	}

}
