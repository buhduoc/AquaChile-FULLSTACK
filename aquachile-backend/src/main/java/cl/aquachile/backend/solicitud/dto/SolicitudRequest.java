package cl.aquachile.backend.solicitud.dto;

import jakarta.validation.constraints.*;import java.util.UUID;
public record SolicitudRequest(@NotNull UUID postulacionId, UUID evaluadorId, String estado, String observaciones) {}
