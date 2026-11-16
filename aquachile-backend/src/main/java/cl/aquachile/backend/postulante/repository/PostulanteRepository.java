package cl.aquachile.backend.postulante.repository;

import cl.aquachile.backend.postulante.entity.Postulante;import java.util.*;import java.util.concurrent.*;import org.springframework.stereotype.Repository;
@Repository public class PostulanteRepository {
 private final Map<UUID,Postulante> data=new ConcurrentHashMap<>();
 public List<Postulante> listar(){return new ArrayList<>(data.values());}
 public Optional<Postulante> obtener(UUID id){return Optional.ofNullable(data.get(id));}
 public Postulante guardar(Postulante value){data.put(value.id(),value);return value;}
 public void eliminar(UUID id){data.remove(id);}
}
