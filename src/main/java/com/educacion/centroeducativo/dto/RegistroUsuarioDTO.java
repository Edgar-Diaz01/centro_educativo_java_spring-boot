package com.educacion.centroeducativo.dto;

import java.util.Set;

public class RegistroUsuarioDTO {
    
    private String nombre;
    private String correo;
    private String contrasena;
    private Set<String> roles; // ["ADMIN", "ESTUDIANTE", "MAESTRO"]

    public RegistroUsuarioDTO() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
