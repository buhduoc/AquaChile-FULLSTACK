package cl.aquachile.backend.postulante.entity;

import java.util.UUID;
public record Postulante(UUID id, String nombre, String correo, String telefono, String rut) {}
