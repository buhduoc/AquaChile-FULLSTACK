package cl.aquachile.backend.usuario.repository;

import cl.aquachile.backend.usuario.entity.Rol;
import cl.aquachile.backend.usuario.entity.Usuario;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UsuarioRepositoryMemoria implements UsuarioRepository {

    private final Map<String, Usuario> usuarios =
            new ConcurrentHashMap<>();

    public UsuarioRepositoryMemoria(PasswordEncoder encoder) {

        Usuario administrador = new Usuario(
                UUID.randomUUID(),
                "Administrador AquaChile",
                "admin@aquachile.cl",
                encoder.encode("Admin123!"),
                Rol.ADMIN,
                true
        );

        usuarios.put(
                administrador.correo().toLowerCase(),
                administrador
        );
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return Optional.ofNullable(
                usuarios.get(correo.toLowerCase())
        );
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return usuarios.containsKey(correo.toLowerCase());
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        usuarios.put(
                usuario.correo().toLowerCase(),
                usuario
        );

        return usuario;
    }
}
