package cl.aquachile.backend.oferta.service;

import cl.aquachile.backend.oferta.repository.OfertaRepository;import cl.aquachile.backend.oferta.entity.Oferta;import cl.aquachile.backend.oferta.dto.OfertaRequest;import org.springframework.stereotype.Service;import org.springframework.web.server.ResponseStatusException;import org.springframework.http.HttpStatus;import java.util.*;
@Service public class OfertaService {
 private final OfertaRepository repo; public OfertaService(OfertaRepository repo){this.repo=repo;}
 public List<Oferta> listar(){return repo.listar();}
 public Oferta obtener(UUID id){return repo.obtener(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Oferta no encontrado"));}
 public Oferta crear(OfertaRequest r){return repo.guardar(new Oferta(UUID.randomUUID(),r.titulo(), r.descripcion(), r.ubicacion(), r.publicada()));}
 public Oferta actualizar(UUID id,OfertaRequest r){obtener(id);return repo.guardar(new Oferta(id,r.titulo(), r.descripcion(), r.ubicacion(), r.publicada()));}
 public void eliminar(UUID id){obtener(id);repo.eliminar(id);}
}
