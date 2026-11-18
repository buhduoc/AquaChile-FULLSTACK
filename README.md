# AquaChile — Gestión de postulaciones y evaluaciones psicolaborales

> **Proyecto académico en desarrollo.** Este repositorio contiene un prototipo de interfaz web y un backend Java/Spring Boot. La conexión local a PostgreSQL se ha probado, pero la persistencia de todos los módulos y la integración completa del frontend con el backend **aún no están verificadas**.

## Objetivo

Desarrollar una aplicación web para apoyar la gestión de candidatos, ofertas laborales, postulaciones y solicitudes de evaluación psicolaboral, con control de acceso y panel de indicadores.

## Tecnologías

| Capa | Tecnologías |
| --- | --- |
| Frontend | React 19, React Router 7, Bootstrap 5, Vite |
| Backend | Java 21, Spring Boot 4.1.2, Maven, API REST |
| Seguridad backend | Spring Security, JWT, BCrypt |
| Base de datos | PostgreSQL 18, JDBC, Spring Data JPA / Hibernate |
| Pruebas frontend | Vitest, Testing Library |
| Herramientas | Git, GitHub, IntelliJ IDEA, pgAdmin 4 |

## Estado de desarrollo (noviembre de 2026)

| Funcionalidad | Estado | Evidencia / siguiente paso |
| --- | --- | --- |
| Interfaz React | Prototipo disponible | Rutas y pruebas descritas en el README original; verificar conexión con API |
| Backend Spring Boot | Probado localmente | Se ejecutaron endpoints REST en pruebas manuales |
| Autenticación JWT | Probada en backend | Login y `/api/auth/me` respondieron correctamente |
| Roles y usuarios | Base funcional en backend | Se creó un usuario mediante API; falta persistencia verificada |
| Ofertas laborales | API base probada | Alta y consulta manual realizadas |
| Postulantes | API base probada | Alta y consulta manual realizadas; revisar duplicados |
| Postulaciones | API base probada | Alta y consulta manual realizadas |
| Solicitudes de evaluación | API base probada | Alta y consulta manual realizadas |
| Dashboard | Resumen REST probado | `/api/dashboard/resumen` respondió con conteos |
| PostgreSQL | Conexión local comprobada | Hibernate identificó PostgreSQL 18 e inicializó JPA |
| Persistencia PostgreSQL | Pendiente de comprobar | Convertir/verificar entidades y repositorios JPA; probar reinicios |
| Integración frontend-backend | Por verificar | Comprobar que pantallas React usan los endpoints reales y no solo `localStorage` |
| Archivos/CV, IA y evaluaciones | Por verificar | Revisar implementación real, seguridad y pruebas |
| Despliegue en la nube | Pendiente | Configurar hosting, secretos, base de datos y almacenamiento de archivos |

**Importante:** las pruebas manuales citadas describen el entorno local durante el desarrollo. No garantizan que el último commit público tenga todos los cambios locales ni que exista cobertura automatizada equivalente.

## Estructura del repositorio

En la versión pública consultada, el frontend está **en la raíz** del repositorio, no dentro de una carpeta `aquachile-frontend`:

```text
AquaChile-FULLSTACK/
├── aquachile-backend/     # Aplicación Java / Spring Boot
├── public/                # Recursos públicos del frontend
├── src/                   # Código React
├── package.json           # Scripts y dependencias frontend
├── vite.config.js
├── .env.example
└── README.md
```

## Requisitos locales

- Java JDK 21 y Maven.
- Node.js y npm.
- PostgreSQL 18 (o versión compatible) y, opcionalmente, pgAdmin 4.
- Base de datos `aquachile_db` y usuario PostgreSQL `aquachile_app` con permisos apropiados.

## Ejecutar el frontend

Desde la **raíz** del repositorio:

```powershell
npm install
npm run dev
```

Vite mostrará la dirección local de acceso, normalmente `http://localhost:5173`.

Para ejecutar las pruebas definidas en `package.json`:

```powershell
npm run test:run
```

## Ejecutar el backend

Iniciar PostgreSQL si el servicio se configuró en modo manual. En PowerShell **como administrador**:

```powershell
Start-Service postgresql-x64-18
```

Desde el directorio del backend:

```powershell
cd .\aquachile-backend
```

Configurar las variables de entorno **en la misma terminal**. No introducir contraseñas ni secretos en Git:

```powershell
$env:DB_PASSWORD = Read-Host 'Contraseña del usuario aquachile_app'

$bytes = New-Object byte[] 32
$rng = [System.Security.Cryptography.RandomNumberGenerator]::Create()
$rng.GetBytes($bytes)
$rng.Dispose()
$env:JWT_SECRET = [Convert]::ToBase64String($bytes)

mvn spring-boot:run
```

> La clave JWT generada en este ejemplo es temporal: los tokens emitidos con una clave anterior dejarán de ser válidos. Para despliegues estables, conservar una clave segura mediante el gestor de secretos del entorno.

Configuración de referencia para `aquachile-backend/src/main/resources/application.properties` (sin reemplazar otras propiedades existentes):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/aquachile_db
spring.datasource.username=aquachile_app
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
app.jwt.secret=${JWT_SECRET}
```

`ddl-auto=update` es útil para desarrollo local; para producción se recomienda gestionar cambios de esquema con migraciones versionadas.

## Endpoints probados manualmente

| Método | Ruta | Uso |
| --- | --- | --- |
| GET | `/api/health` | Estado del backend |
| POST | `/api/auth/login` | Inicio de sesión y token JWT |
| GET | `/api/auth/me` | Usuario autenticado |
| POST | `/api/usuarios` | Alta de usuario |
| GET / POST | `/api/ofertas` | Consulta y creación de ofertas |
| GET / POST | `/api/postulantes` | Consulta y creación de postulantes |
| GET / POST | `/api/postulaciones` | Consulta y creación de postulaciones |
| GET / POST | `/api/solicitudes` | Consulta y creación de solicitudes |
| GET | `/api/dashboard/resumen` | Conteos generales |

Los endpoints protegidos requieren un token Bearer válido y permisos adecuados. Este listado no constituye una especificación completa de la API.

## Interfaz existente

El frontend público incluye rutas para inicio, indicadores, candidatos, solicitudes, cuentas, usuarios, perfil, opciones y solicitud pública de acceso. Su documentación anterior describe almacenamiento mediante `localStorage` y cuentas de demostración. **No se debe confundir ese mecanismo de prueba con la autenticación JWT del backend**: hay que revisar y unificar ambos flujos antes de considerar terminada la integración.

## Próximas tareas prioritarias

1. Verificar y migrar entidades/repositorios de usuarios, ofertas, postulantes, postulaciones y solicitudes a JPA.
2. Comprobar persistencia real: crear un registro, reiniciar Spring Boot y consultar nuevamente.
3. Integrar React con la API REST y sustituir el almacenamiento local en los flujos que deban ser compartidos.
4. Revisar validaciones, unicidad de postulantes, relaciones entre tablas y permisos por rol.
5. Implementar o verificar carga segura de CV/archivos y evaluaciones, según la guía académica.
6. Añadir pruebas de integración, migraciones SQL, documentación de API y preparación de despliegue.

## Seguridad

- No subir `.env`, contraseñas de PostgreSQL, tokens JWT ni claves privadas.
- Mantener `target/`, `node_modules/` y `dist/` fuera del control de versiones.
- Usar cuentas de demostración únicamente en desarrollo y cambiar credenciales antes de publicar.
- No exponer PostgreSQL directamente al navegador: React debe comunicarse con el backend.

## Alcance de esta documentación

Este README refleja el repositorio público consultado y las pruebas locales realizadas durante el desarrollo. **No es una certificación de cumplimiento de la pauta académica**: para evaluar cada requisito formal se necesita la guía/rúbrica original y revisar la implementación correspondiente.
