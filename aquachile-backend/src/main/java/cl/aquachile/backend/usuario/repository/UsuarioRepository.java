package cl.aquachile.backend.usuario.repository;

import cl.aquachile.backend.usuario.entity.Usuario;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {
    Optional<Usuario> buscarPorId(UUID id);
    Optional<Usuario> buscarPorCorreo(String correo);
    boolean existePorCorreo(String correo);
    List<Usuario> listar();
    Usuario guardar(Usuario usuario);
}
