package cl.aquachile.backend.usuario.service;

import cl.aquachile.backend.usuario.entity.Usuario;
import cl.aquachile.backend.usuario.repository.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.buscarPorCorreo(correo);
    }

    public boolean existePorCorreo(String correo) {
        return usuarioRepository.existePorCorreo(correo);
    }
}
