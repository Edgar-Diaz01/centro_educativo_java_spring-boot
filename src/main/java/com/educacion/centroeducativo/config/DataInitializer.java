package com.educacion.centroeducativo.config;

import com.educacion.centroeducativo.entity.Rol;
import com.educacion.centroeducativo.repository.RolRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private RolRepository rolRepository;

    @PostConstruct
    public void init() {
        crearRolSiNoExiste("ADMIN");
        crearRolSiNoExiste("MAESTRO");
        crearRolSiNoExiste("ESTUDIANTE");
    }

    private void crearRolSiNoExiste(String nombre) {
        rolRepository.findByNombre(nombre).orElseGet(() -> {
            Rol rol = new Rol();
            rol.setNombre(nombre);
            return rolRepository.save(rol);
        });
    }
}
