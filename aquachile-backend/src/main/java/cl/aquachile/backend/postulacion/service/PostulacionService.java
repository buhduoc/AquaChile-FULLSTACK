package cl.aquachile.backend.postulacion.service;

import cl.aquachile.backend.postulacion.repository.PostulacionRepository;import cl.aquachile.backend.postulacion.entity.Postulacion;import cl.aquachile.backend.postulacion.dto.PostulacionRequest;import org.springframework.stereotype.Service;import org.springframework.web.server.ResponseStatusException;import org.springframework.http.HttpStatus;import java.util.*;
@Service public class PostulacionService {
 private final PostulacionRepository repo; public PostulacionService(PostulacionRepository repo){this.repo=repo;}
 public List<Postulacion> listar(){return repo.listar();}
 public Postulacion obtener(UUID id){return repo.obtener(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Postulacion no encontrado"));}
 public Postulacion crear(PostulacionRequest r){return repo.guardar(new Postulacion(UUID.randomUUID(),r.ofertaId(), r.postulanteId(), r.estado()));}
 public Postulacion actualizar(UUID id,PostulacionRequest r){obtener(id);return repo.guardar(new Postulacion(id,r.ofertaId(), r.postulanteId(), r.estado()));}
 public void eliminar(UUID id){obtener(id);repo.eliminar(id);}
}
