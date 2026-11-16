package cl.aquachile.backend.oferta.dto;

import jakarta.validation.constraints.*;import java.util.UUID;
public record OfertaRequest(@NotBlank String titulo, String descripcion, String ubicacion, boolean publicada) {}
