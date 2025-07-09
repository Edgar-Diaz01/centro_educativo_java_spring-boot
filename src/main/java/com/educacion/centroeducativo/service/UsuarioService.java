package com.educacion.centroeducativo.service; // Define el paquete al que pertenece esta clase.
                                                // Las clases de servicio se suelen organizar en un paquete 'service'.

import com.educacion.centroeducativo.entity.Usuario;     // Importa la clase 'Usuario', nuestra entidad de datos.
import com.educacion.centroeducativo.repository.UsuarioRepository; // Importa la interfaz 'UsuarioRepository',
                                                                // que nos permite interactuar con la base de datos para la entidad Usuario.
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación @Autowired de Spring.
                                                              // Se usa para inyectar dependencias automáticamente.
import org.springframework.stereotype.Service;              // Importa la anotación @Service de Spring.
                                                                // Marca esta clase como un componente de servicio de Spring,
                                                                // lo que permite que Spring la detecte y la gestione.

import java.util.List;   // Importa la interfaz 'List' para colecciones de objetos.
import java.util.Optional; // Importa la clase 'Optional' para manejar valores que pueden ser nulos de forma segura.
import com.educacion.centroeducativo.entity.Rol;
import com.educacion.centroeducativo.repository.RolRepository;
import java.util.HashSet;
import java.util.Set;

@Service // Anotación de Spring que indica que esta clase es un 'Service Component'.
         // Spring la registrará como un bean en su contexto de aplicación y la gestionará.
         // Esto permite la inyección de dependencias y la aplicación de lógica de negocio.
public class UsuarioService {

    @Autowired // Anotación de Spring que se usa para la inyección de dependencias.
               // Spring buscará una instancia de 'UsuarioRepository' y la asignará automáticamente
               // a esta variable, sin que necesitemos instanciarla manualmente.
    private UsuarioRepository usuarioRepository; // Declara una instancia de 'UsuarioRepository'.
                                               // A través de este objeto, el servicio interactuará con la base de datos.
    private RolRepository rolRepository;
    // --- Métodos de Lógica de Negocio ---
    // Cada método encapsula una operación de negocio relacionada con la gestión de usuarios.

    /**
     * Registra un nuevo usuario en la base de datos.
     * Realiza una validación para asegurar que el correo electrónico no esté ya registrado.
     * @param usuario El objeto Usuario a registrar.
     * @return El objeto Usuario guardado en la base de datos.
     * @throws RuntimeException Si el correo electrónico ya está registrado.
     */
    public Usuario registrarUsuario(Usuario usuario) {
        // Verifica si ya existe un usuario con el mismo correo electrónico.
        // Utiliza el método 'existsByCorreo' definido en 'UsuarioRepository'.
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            // Si el correo ya existe, lanza una excepción para indicar el error.
            // En una aplicación real, se preferiría lanzar una excepción más específica y manejarla adecuadamente.
            throw new RuntimeException("El correo ya está registrado");
        }
        
        //busca el rol estudiante
        Rol rolEstudiante = rolRepository.findByNombre("ESTUDIANTE").orElseThrow(() -> new RuntimeException("Rol ESTUDIANTE no existe en la base de datos"));
        usuario.getRoles().add(rolEstudiante);
        
        if (rolEstudiante == null){
            throw new RuntimeException("Rol ESTUDIANTE no existe en la base de datos");
        }
        
        // Asignar el rol al nuevo usuario
        Set<Rol> roles = new HashSet<>();
        roles.add(rolEstudiante);
        usuario.setRoles(roles);
        
        
        // Si el correo no está registrado, guarda el nuevo usuario en la base de datos.
        // Utiliza el método 'save' proporcionado por JpaRepository.
        return usuarioRepository.save(usuario);
    }

    /**
     * Obtiene una lista de todos los usuarios registrados en la base de datos.
     * @return Una lista de objetos Usuario.
     */
    public List<Usuario> obtenerTodos() {
        // Recupera todos los usuarios de la base de datos.
        // Utiliza el método 'findAll' proporcionado por JpaRepository.
        return usuarioRepository.findAll();
    }

    /**
     * Busca un usuario por su ID único.
     * @param id El ID del usuario a buscar.
     * @return Un Optional que contiene el Usuario si se encuentra, o un Optional vacío si no.
     */
    public Optional<Usuario> obtenerPorId(Long id) {
        // Busca un usuario por su ID en la base de datos.
        // Utiliza el método 'findById' proporcionado por JpaRepository.
        // Retorna un Optional<Usuario> para manejar la posible ausencia del usuario de forma segura.
        return usuarioRepository.findById(id);
    }

    /**
     * Busca un usuario por su dirección de correo electrónico.
     * @param correo La dirección de correo electrónico del usuario a buscar.
     * @return Un Optional que contiene el Usuario si se encuentra, o un Optional vacío si no.
     */
    public Optional<Usuario> buscarPorCorreo(String correo) {
        // Busca un usuario por su correo electrónico.
        // Utiliza el método 'findByCorreo' que definimos en 'UsuarioRepository'.
        // Retorna un Optional<Usuario> para manejar la posible ausencia del usuario de forma segura.
        return usuarioRepository.findByCorreo(correo);
    }
    
    public Usuario login(String correo, String contrasena) {
    Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreo(correo);
    
    if (usuarioOptional.isPresent()) {
        Usuario usuario = usuarioOptional.get();
        if (usuario.getContrasena().equals(contrasena)) {
            return usuario;
        } else {
            throw new RuntimeException("Contraseña incorrecta");
        }
    } else {
        throw new RuntimeException("Usuario no encontrado");
    }
}
   public void asignarRolAUsuario(String correoUsuario, String nombreRol) {
    Usuario usuario = usuarioRepository.findByCorreo(correoUsuario)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correoUsuario));

    Rol rol = rolRepository.findByNombre(nombreRol)
        .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + nombreRol));

    usuario.getRoles().add(rol);
    usuarioRepository.save(usuario);
}
 
    

}