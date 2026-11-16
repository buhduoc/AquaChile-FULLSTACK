package cl.aquachile.backend.postulacion.entity;

import java.util.UUID;
public record Postulacion(UUID id, UUID ofertaId, UUID postulanteId, String estado) {}
