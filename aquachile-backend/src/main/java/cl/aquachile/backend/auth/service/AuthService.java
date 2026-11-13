package cl.aquachile.backend.auth.service;

import cl.aquachile.backend.auth.dto.LoginRequest;
import cl.aquachile.backend.auth.dto.LoginResponse;
import cl.aquachile.backend.usuario.entity.Usuario;
import cl.aquachile.backend.usuario.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    @Value("${app.jwt.expiration}")
    private long expiracion;

    public AuthService(
            AuthenticationManager authenticationManager,
            UsuarioRepository usuarioRepository,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.correo(),
                            request.password()
                    )
            );

        } catch (AuthenticationException exception) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Correo o contraseña incorrectos"
            );
        }

        Usuario usuario = usuarioRepository
                .buscarPorCorreo(request.correo())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "Usuario no encontrado"
                        )
                );

        String token = jwtService.generarToken(
                usuario.correo(),
                usuario.rol()
        );

        return new LoginResponse(
                token,
                "Bearer",
                usuario.correo(),
                usuario.nombre(),
                usuario.rol(),
                expiracion
        );

    }
}
