package com.educacion.centroeducativo.entity; // Define el paquete donde se encuentra esta clase.

import jakarta.persistence.*; // Importa todas las clases necesarias de Jakarta Persistence (JPA) para la persistencia de datos.
import java.util.HashSet;
import java.util.Set;

@Entity // Declara que esta clase es una entidad JPA, lo que significa que se mapeará a una tabla en la base de datos.
@Table(name = "roles") // Especifica el nombre de la tabla en la base de datos a la que se mapeará esta entidad. En este caso, la tabla se llamará "roles".
public class Rol { // Define la clase Rol.

    @Id // Marca este campo como la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la estrategia de generación de valores para la clave primaria. IDENTITY indica que la base de datos generará automáticamente los valores (ej. auto-incremento).
    private Long id; // Declara el campo 'id' de tipo Long, que representará el identificador único del rol.

    @Column(nullable = false, unique = true) // Mapea este campo a una columna en la tabla. 'nullable = false' indica que la columna no puede ser nula. 'unique = true' asegura que los valores en esta columna sean únicos.
    private String nombre; // Declara el campo 'nombre' de tipo String, que representará el nombre del rol.

    public Rol() {} // Constructor vacío por defecto. Es requerido por JPA.

    public Rol(String nombre) { // Constructor que permite inicializar un objeto Rol con un nombre.
        this.nombre = nombre; // Asigna el valor del parámetro 'nombre' al campo 'nombre' de la instancia actual.
    }

    // --- Getters y setters ---

    public Long getId() { // Método 'getter' para el campo 'id'.
        return id; // Retorna el valor del 'id'.
    }

    public String getNombre() { // Método 'getter' para el campo 'nombre'.
        return nombre; // Retorna el valor del 'nombre'.
    }

    public void setNombre(String nombre) { // Método 'setter' para el campo 'nombre'.
        this.nombre = nombre; // Establece el valor del 'nombre'.
    }
    
    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios = new HashSet<>();

}
