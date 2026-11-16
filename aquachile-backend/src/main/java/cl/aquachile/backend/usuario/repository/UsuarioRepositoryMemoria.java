package cl.aquachile.backend.usuario.repository;

import cl.aquachile.backend.usuario.entity.Rol;
import cl.aquachile.backend.usuario.entity.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class UsuarioRepositoryMemoria implements UsuarioRepository {
    private final ConcurrentMap<UUID, Usuario> usuarios = new ConcurrentHashMap<>();

    public UsuarioRepositoryMemoria(PasswordEncoder encoder) {
        Usuario admin = new Usuario(UUID.randomUUID(), "Administrador AquaChile",
                "admin@aquachile.cl", encoder.encode("Admin123!"), Rol.ADMIN, true);
        guardar(admin);
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        if (correo == null) return Optional.empty();
        String normalizado = correo.trim().toLowerCase(Locale.ROOT);
        return usuarios.values().stream().filter(u -> u.correo().equals(normalizado)).findFirst();
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return buscarPorCorreo(correo).isPresent();
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> resultado = new ArrayList<>(usuarios.values());
        resultado.sort(Comparator.comparing(Usuario::nombre, String.CASE_INSENSITIVE_ORDER));
        return resultado;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        usuarios.put(usuario.id(), usuario);
        return usuario;
    }
}
