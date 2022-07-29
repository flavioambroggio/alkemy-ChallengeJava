package com.alkemy.CHALLENGE.BACKEND.configurations;

import com.alkemy.CHALLENGE.BACKEND.models.Usuario;
import com.alkemy.CHALLENGE.BACKEND.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName-> {

            Usuario usuario = usuarioRepository.findByEmail(inputName);

            if (usuario != null) {

                if(usuario.getEmail().equals("admin@admin.com")) {

                    return new User(usuario.getEmail(), usuario.getPassword(),

                            AuthorityUtils.createAuthorityList("ADMIN"));
                } else {

                    return new User(usuario.getEmail(), usuario.getPassword(),

                            AuthorityUtils.createAuthorityList("USER"));
                }

            } else {

                throw new UsernameNotFoundException("Unknown user: " + inputName);

            }

        });

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
