package cl.aquachile.backend.usuario.repository;

import cl.aquachile.backend.usuario.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> buscarPorCorreo(String correo);

    boolean existePorCorreo(String correo);

    Usuario guardar(Usuario usuario);
}
