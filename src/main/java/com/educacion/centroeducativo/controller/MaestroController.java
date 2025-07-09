package com.educacion.centroeducativo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/maestro")
public class MaestroController {

    @GetMapping("/dashboard")
    public String maestroDashboard() {
        return "Acceso permitido al panel de MAESTRO";
    }
}