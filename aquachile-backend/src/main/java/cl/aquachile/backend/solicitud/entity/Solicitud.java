package cl.aquachile.backend.solicitud.entity;

import java.util.UUID;
public record Solicitud(UUID id, UUID postulacionId, UUID evaluadorId, String estado, String observaciones) {}
