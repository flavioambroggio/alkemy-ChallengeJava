package com.alkemy.CHALLENGE.BACKEND.services.implement;

import com.alkemy.CHALLENGE.BACKEND.models.Usuario;
import com.alkemy.CHALLENGE.BACKEND.repositories.UsuarioRepository;
import com.alkemy.CHALLENGE.BACKEND.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImplement implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario traerUsuarioAutenticado(Authentication authentication) {
        return usuarioRepository.findByEmail(authentication.getName());
    }

    @Override
    public Usuario traerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}
