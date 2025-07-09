package com.educacion.centroeducativo.controller;

import com.educacion.centroeducativo.dto.LoginRequestDTO;
import com.educacion.centroeducativo.entity.Usuario;
import com.educacion.centroeducativo.security.util.JwtUtil;
import com.educacion.centroeducativo.service.UsuarioDetailsService;
import com.educacion.centroeducativo.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO request) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasena())
        );

        return jwtUtil.generateToken(request.getCorreo());
    }

    @PostMapping("/register")
    public Usuario registrar(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }
}
