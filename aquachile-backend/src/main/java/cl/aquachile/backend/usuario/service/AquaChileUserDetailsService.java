package cl.aquachile.backend.usuario.service;

import cl.aquachile.backend.usuario.entity.Usuario;
import cl.aquachile.backend.usuario.repository.UsuarioRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AquaChileUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public AquaChileUserDetailsService(
            UsuarioRepository usuarioRepository
    ) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.buscarPorCorreo(correo)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado")
                );

        return User.builder()
                .username(usuario.correo())
                .password(usuario.passwordHash())
                .authorities("ROLE_" + usuario.rol().name())
                .disabled(!usuario.activo())
                .build();
    }
}
