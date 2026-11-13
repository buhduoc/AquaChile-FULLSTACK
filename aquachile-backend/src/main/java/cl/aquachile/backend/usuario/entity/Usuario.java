package cl.aquachile.backend.usuario.entity;

import java.util.UUID;

public record Usuario(
        UUID id,
        String nombre,
        String correo,
        String passwordHash,
        Rol rol,
        boolean activo
) {}
