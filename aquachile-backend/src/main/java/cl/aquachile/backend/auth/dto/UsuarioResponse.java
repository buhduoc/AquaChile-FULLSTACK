package cl.aquachile.backend.auth.dto;

import cl.aquachile.backend.usuario.entity.Rol;

import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nombre,
        String correo,
        Rol rol,
        boolean activo
) {}
