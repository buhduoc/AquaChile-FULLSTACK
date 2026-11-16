package cl.aquachile.backend.postulacion.repository;

import cl.aquachile.backend.postulacion.entity.Postulacion;import java.util.*;import java.util.concurrent.*;import org.springframework.stereotype.Repository;
@Repository public class PostulacionRepository {
 private final Map<UUID,Postulacion> data=new ConcurrentHashMap<>();
 public List<Postulacion> listar(){return new ArrayList<>(data.values());}
 public Optional<Postulacion> obtener(UUID id){return Optional.ofNullable(data.get(id));}
 public Postulacion guardar(Postulacion value){data.put(value.id(),value);return value;}
 public void eliminar(UUID id){data.remove(id);}
}
