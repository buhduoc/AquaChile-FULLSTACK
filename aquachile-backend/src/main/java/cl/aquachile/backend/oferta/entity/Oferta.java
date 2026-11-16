package cl.aquachile.backend.oferta.entity;

import java.util.UUID;
public record Oferta(UUID id, String titulo, String descripcion, String ubicacion, boolean publicada) {}
