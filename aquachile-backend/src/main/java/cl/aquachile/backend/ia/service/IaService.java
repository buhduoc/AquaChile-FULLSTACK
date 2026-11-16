package cl.aquachile.backend.ia.service;

import org.springframework.stereotype.Service;import java.util.*;
@Service public class IaService {
 public Map<String,Object> analizar(String texto){return Map.of("modo","DEMO_SIN_MODELO","resumen","Se recibieron "+texto.trim().split("\s+").length+" palabras para revision humana.","recomendacion","La evaluacion debe realizarla una persona responsable.");}
}
