package com.educacion.centroeducativo.controller;

import com.educacion.centroeducativo.dto.AsignarRolDTO;
import com.educacion.centroeducativo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "Acceso permitido al panel de ADMIN";
    }
    
    @Autowired
private UsuarioService usuarioService;

@PostMapping("/asignar-rol")
public String asignarRol(@RequestBody AsignarRolDTO dto) {
    usuarioService.asignarRolAUsuario(dto.getCorreoUsuario(), dto.getNombreRol());
    return "Rol asignado correctamente";
}

}
