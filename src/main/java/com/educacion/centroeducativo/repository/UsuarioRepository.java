package com.educacion.centroeducativo.repository; // Define el paquete al que pertenece esta interfaz.
                                                // Es una convención para organizar las interfaces de repositorio de la aplicación.

import com.educacion.centroeducativo.entity.Usuario; // Importa la clase 'Usuario'.
                                                    // Esta es la entidad (clase que mapea a la tabla de la base de datos)
                                                    // con la que este repositorio interactuará.
import org.springframework.data.jpa.repository.JpaRepository; // Importa la interfaz 'JpaRepository' de Spring Data JPA.
                                                            // Es la interfaz base que proporciona funcionalidades CRUD (Crear, Leer, Actualizar, Borrar)
                                                            // y otras operaciones de base de datos de forma automática.
import java.util.Optional; // Importa la clase 'Optional' de Java.
                           // Se usa para encapsular un valor que podría estar presente o no.
                           // Ayuda a manejar casos donde una búsqueda no arroja resultados, evitando 'NullPointerExceptions'.

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Declara la interfaz 'UsuarioRepository'.
    // Al extender 'JpaRepository<Usuario, Long>', esta interfaz hereda automáticamente:
    // - Métodos para guardar, buscar por ID, eliminar, y listar todos los usuarios, entre otros.
    // - 'Usuario' es la entidad con la que trabaja este repositorio.
    // - 'Long' es el tipo de la clave primaria de la entidad 'Usuario' (el 'id' es de tipo Long).

    // --- Métodos de consulta personalizados (Query Methods) ---
    // Spring Data JPA puede generar automáticamente la implementación de estos métodos
    // basándose en su nombre, siguiendo una convención específica.

    // Buscar por correo (para login o validación)
    Optional<Usuario> findByCorreo(String correo);
    // Este método buscará un 'Usuario' en la base de datos por su 'correo'.
    // - 'findBy': Es el prefijo que indica a Spring Data JPA que debe realizar una búsqueda.
    // - 'Correo': Hace referencia al atributo 'correo' de la entidad 'Usuario'.
    // - 'Optional<Usuario>': El tipo de retorno indica que puede que se encuentre un usuario o no.
    //                         Si se encuentra, el 'Usuario' estará dentro del 'Optional'; de lo contrario, estará vacío.

    // Verificar existencia por correo (para evitar duplicados al registrar)
    boolean existsByCorreo(String correo);
    // Este método verifica si ya existe un 'Usuario' con un 'correo' específico en la base de datos.
    // - 'existsBy': Es el prefijo que indica a Spring Data JPA que debe verificar la existencia.
    // - 'Correo': Hace referencia al atributo 'correo' de la entidad 'Usuario'.
    // - 'boolean': El tipo de retorno es verdadero (true) si se encuentra un usuario con ese correo,
    //              o falso (false) si no existe ninguno. Es eficiente ya que no recupera el objeto completo.
}