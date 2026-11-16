package cl.aquachile.backend.solicitud.service;

import cl.aquachile.backend.solicitud.repository.SolicitudRepository;import cl.aquachile.backend.solicitud.entity.Solicitud;import cl.aquachile.backend.solicitud.dto.SolicitudRequest;import org.springframework.stereotype.Service;import org.springframework.web.server.ResponseStatusException;import org.springframework.http.HttpStatus;import java.util.*;
@Service public class SolicitudService {
 private final SolicitudRepository repo; public SolicitudService(SolicitudRepository repo){this.repo=repo;}
 public List<Solicitud> listar(){return repo.listar();}
 public Solicitud obtener(UUID id){return repo.obtener(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Solicitud no encontrado"));}
 public Solicitud crear(SolicitudRequest r){return repo.guardar(new Solicitud(UUID.randomUUID(),r.postulacionId(), r.evaluadorId(), r.estado(), r.observaciones()));}
 public Solicitud actualizar(UUID id,SolicitudRequest r){obtener(id);return repo.guardar(new Solicitud(id,r.postulacionId(), r.evaluadorId(), r.estado(), r.observaciones()));}
 public void eliminar(UUID id){obtener(id);repo.eliminar(id);}
}
