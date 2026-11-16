AQUACHILE - MODULO AUTH + USUARIOS
================================
Compatibilidad esperada: Java 21, Spring Boot 4.1.2, Spring Security 7, JJWT 0.12.6.

INSTALACION
1. Copiar la carpeta src de este ZIP en la raiz de aquachile-backend, combinando carpetas y reemplazando los archivos del mismo nombre.
2. NO borrar las otras carpetas del backend ni reemplazar pom.xml.
3. Confirmar en pom.xml spring-boot-starter-security, spring-boot-starter-validation, spring-boot-starter-webmvc y jjwt-api, jjwt-impl y jjwt-jackson 0.12.6.
4. Agregar a src/main/resources/application.properties:
   app.jwt.secret=${JWT_SECRET}
   app.jwt.expiration=86400000
5. En PowerShell, generar el secreto y arrancar en ESA MISMA consola:
   $bytes = New-Object byte[] 32
   [System.Security.Cryptography.RandomNumberGenerator]::Fill($bytes)
   $env:JWT_SECRET = [Convert]::ToBase64String($bytes)
   .\mvnw.cmd clean package -DskipTests
   .\mvnw.cmd spring-boot:run

ENDPOINTS
POST  /api/auth/login                PUBLICO
GET   /api/auth/me                   AUTENTICADO
GET   /api/usuarios                  ADMIN
GET   /api/usuarios/{id}             ADMIN
POST  /api/usuarios                  ADMIN
PUT   /api/usuarios/{id}             ADMIN
PATCH /api/usuarios/{id}/estado      ADMIN
PATCH /api/usuarios/{id}/password    ADMIN

EJEMPLOS JSON
Login: {"correo":"admin@aquachile.cl","password":"Admin123!"}
Crear: {"nombre":"Analista","correo":"analista@aquachile.cl","password":"Analista123!","rol":"ANALISTA_RECLUTAMIENTO"}
Actualizar: {"nombre":"Analista Dos","correo":"analista2@aquachile.cl","rol":"EVALUADOR"}
Estado: {"activo":false}
Password: {"nuevaPassword":"NuevaClave123!"}

Enviar Authorization: Bearer <token> para todas las rutas protegidas.

IMPORTANTE
- Los datos viven en memoria y se reinician al reiniciar la app.
- Admin de desarrollo: admin@aquachile.cl / Admin123!; eliminar en produccion.
- No hay logout del lado servidor ni revocacion inmediata de JWT; logout del cliente elimina el token.
- Tras cambiar correo, iniciar sesion de nuevo.
- Cambiar JWT_SECRET invalida tokens emitidos con el secreto anterior.
- El modulo no incluye todavia PostgreSQL, recuperacion de contrasena ni pruebas automatizadas.
- No se ha compilado contra tu proyecto local porque no se adjunto el repositorio.
