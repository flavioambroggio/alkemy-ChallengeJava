package com.alkemy.CHALLENGE.BACKEND.controllers;

import com.alkemy.CHALLENGE.BACKEND.models.Usuario;
import com.alkemy.CHALLENGE.BACKEND.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

import static com.alkemy.CHALLENGE.BACKEND.utils.UsuarioUtils.generarEmail;

@RestController
public class UsuarioController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/auth/register")
    public ResponseEntity<Object> registro (@RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String password) throws MessagingException, UnsupportedEncodingException {

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>("Faltan Datos", HttpStatus.FORBIDDEN);
        }

        if (usuarioService.traerUsuarioPorEmail(email) != null){
            return new ResponseEntity<>("El email ya esta en uso", HttpStatus.FORBIDDEN);
        }

        Usuario usuario = new Usuario(nombre, apellido, email, passwordEncoder.encode(password));
        usuarioService.guardarUsuario(usuario);

        enviarMail(usuario.getEmail());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public void enviarMail(String email) throws MessagingException, UnsupportedEncodingException {
        Usuario usuario = usuarioService.traerUsuarioPorEmail(email);

        String toAdress = email;
        String fromAddress = "clinicamedihub@gmail.com";
        String senderName = "DisneyApp";
        String subject = "Email de bienvenida";
        String content = generarEmail();
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);

        content = content.replace("[usuario]", usuario.getFullName());

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAdress);
        helper.setSubject(subject);
        helper.setText(content, true);
        javaMailSender.send(mensaje);
    }


}
