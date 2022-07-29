package com.alkemy.CHALLENGE.BACKEND.services;

import com.alkemy.CHALLENGE.BACKEND.models.Usuario;
import org.springframework.security.core.Authentication;

public interface UsuarioService {

    Usuario traerUsuarioAutenticado (Authentication authentication);

    Usuario traerUsuarioPorEmail (String email);

    void guardarUsuario (Usuario usuario);
}
