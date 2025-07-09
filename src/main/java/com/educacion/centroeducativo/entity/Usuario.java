package com.educacion.centroeducativo.entity; // Define el paquete al que pertenece esta clase.
                                        // Los paquetes ayudan a organizar las clases Java en una estructura lógica.

import jakarta.persistence.*; // Importa todas las clases y anotaciones de Jakarta Persistence API (JPA).
import java.util.HashSet;
import java.util.Set;
                            // JPA es un estándar de Java para mapear objetos Java a tablas de bases de datos relacionales.

@Entity // Anotación de JPA que indica que esta clase es una entidad.
        // Esto significa que las instancias de esta clase pueden ser gestionadas por un EntityManager y mapeadas a una tabla de base de datos.
@Table(name = "usuarios") // Anotación de JPA que especifica el nombre de la tabla en la base de datos
                          // a la que se mapeará esta entidad. En este caso, la tabla se llamará "usuarios".
public class Usuario {

    @Id // Anotación de JPA que marca este campo como la clave primaria de la tabla.
        // La clave primaria es un identificador único para cada registro en la base de datos.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotación que especifica cómo se generará el valor de la clave primaria.
                                                        // GenerationType.IDENTITY indica que la base de datos se encargará de generar
                                                        // un valor único automáticamente (ej. auto-incremento en MySQL o PostgreSQL).
    private Long id; // Campo que representa el ID único del usuario. Será la columna 'id' en la tabla 'usuarios'.

    private String nombre; // Campo que representa el nombre del usuario. Será la columna 'nombre' en la tabla.

    @Column(unique = true) // Anotación de JPA que indica que el valor de esta columna debe ser único en la tabla.
                            // Esto asegura que no puedan existir dos usuarios con el mismo correo electrónico.
    private String correo; // Campo que representa el correo electrónico del usuario. Será la columna 'correo' en la tabla.

    private String contrasena; // Campo que representa la contraseña del usuario. Será la columna 'contrasena' en la tabla.
                               // ¡Importante!: En una aplicación real, las contraseñas nunca deben almacenarse en texto plano.
                               // Siempre deben ser cifradas (hasheadas) antes de guardarlas en la base de datos por seguridad.


    // --- Getters y Setters ---
    // Estos métodos proporcionan acceso controlado (encapsulación) a los atributos de la clase.
    // Los 'getters' permiten leer el valor de un atributo, y los 'setters' permiten modificarlo.

    public Long getId() {
        return id; // Devuelve el valor del ID del usuario.
    }

    public void setId(Long id) {
        this.id = id; // Establece el valor del ID del usuario. 'this.id' se refiere al atributo de la clase.
    }

    public String getNombre() {
        return nombre; // Devuelve el nombre del usuario.
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; // Establece el nombre del usuario.
    }

    public String getCorreo() {
        return correo; // Devuelve el correo electrónico del usuario.
    }

    public void setCorreo(String correo) {
        this.correo = correo; // Establece el correo electrónico del usuario.
    }

    public String getContrasena() {
        return contrasena; // Devuelve la contraseña del usuario.
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena; // Establece la contraseña del usuario.
    }

    
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    name = "usuarios_roles",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();
    
    public Set<Rol> getRoles(){
        return roles;
    }
    
    public void setRoles(Set<Rol> roles){
        this.roles = roles;
    }

    
}