package cl.aquachile.backend.solicitud.repository;

import cl.aquachile.backend.solicitud.entity.Solicitud;import java.util.*;import java.util.concurrent.*;import org.springframework.stereotype.Repository;
@Repository public class SolicitudRepository {
 private final Map<UUID,Solicitud> data=new ConcurrentHashMap<>();
 public List<Solicitud> listar(){return new ArrayList<>(data.values());}
 public Optional<Solicitud> obtener(UUID id){return Optional.ofNullable(data.get(id));}
 public Solicitud guardar(Solicitud value){data.put(value.id(),value);return value;}
 public void eliminar(UUID id){data.remove(id);}
}
