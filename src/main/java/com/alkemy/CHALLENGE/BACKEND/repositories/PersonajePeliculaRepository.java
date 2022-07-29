package com.alkemy.CHALLENGE.BACKEND.repositories;

import com.alkemy.CHALLENGE.BACKEND.models.PersonajePelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PersonajePeliculaRepository extends JpaRepository<PersonajePelicula, Long> {
}
