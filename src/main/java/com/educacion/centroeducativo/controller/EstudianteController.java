package com.educacion.centroeducativo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {

    @GetMapping("/dashboard")
    public String estudianteDashboard() {
        return "Acceso permitido al panel de ESTUDIANTE";
    }
}
