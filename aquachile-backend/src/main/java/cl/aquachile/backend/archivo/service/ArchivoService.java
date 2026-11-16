package cl.aquachile.backend.archivo.service;

import org.springframework.stereotype.Service;import org.springframework.beans.factory.annotation.Value;import org.springframework.web.multipart.MultipartFile;import org.springframework.web.server.ResponseStatusException;import org.springframework.http.HttpStatus;import java.nio.file.*;import java.io.IOException;import java.util.*;
@Service public class ArchivoService {
 private final Path root; private final Map<UUID,String> names=new java.util.concurrent.ConcurrentHashMap<>();
 public ArchivoService(@Value("${app.upload.dir:./uploads}") String dir){root=Path.of(dir).toAbsolutePath().normalize();}
 public UUID guardar(MultipartFile file){
  String original=Objects.toString(file.getOriginalFilename(),"").toLowerCase(Locale.ROOT);
  if(file.isEmpty()||file.getSize()>5_000_000||!(original.endsWith(".pdf")||original.endsWith(".docx")))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"CV debe ser PDF o DOCX, maximo 5 MB");
  UUID id=UUID.randomUUID();String ext=original.endsWith(".pdf")?".pdf":".docx";
  try{Files.createDirectories(root);Files.copy(file.getInputStream(),root.resolve(id+ext),StandardCopyOption.REPLACE_EXISTING);names.put(id,ext);return id;}
  catch(IOException e){throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"No se pudo guardar archivo");}
 }
 public byte[] leer(UUID id){String ext=names.get(id);if(ext==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Archivo no encontrado");try{return Files.readAllBytes(root.resolve(id+ext));}catch(IOException e){throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Archivo no encontrado");}}
}
