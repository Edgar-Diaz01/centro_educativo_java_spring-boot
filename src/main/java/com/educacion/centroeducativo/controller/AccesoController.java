package com.educacion.centroeducativo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccesoController {

    @GetMapping("/api/admin/acceso")
    public String accesoAdmin() {
        return "✅ Acceso permitido: ADMIN";
    }

    @GetMapping("/api/maestro/acceso")
    public String accesoMaestro() {
        return "✅ Acceso permitido: MAESTRO";
    }

    @GetMapping("/api/estudiante/acceso")
    public String accesoEstudiante() {
        return "✅ Acceso permitido: ESTUDIANTE";
    }
}
