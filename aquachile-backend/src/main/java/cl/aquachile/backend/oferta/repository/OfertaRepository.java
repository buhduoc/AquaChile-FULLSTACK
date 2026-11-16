package cl.aquachile.backend.oferta.repository;

import cl.aquachile.backend.oferta.entity.Oferta;import java.util.*;import java.util.concurrent.*;import org.springframework.stereotype.Repository;
@Repository public class OfertaRepository {
 private final Map<UUID,Oferta> data=new ConcurrentHashMap<>();
 public List<Oferta> listar(){return new ArrayList<>(data.values());}
 public Optional<Oferta> obtener(UUID id){return Optional.ofNullable(data.get(id));}
 public Oferta guardar(Oferta value){data.put(value.id(),value);return value;}
 public void eliminar(UUID id){data.remove(id);}
}
