# AquaChile FULLSTACK

Aplicación académica de gestión de postulantes, solicitudes de acceso y evaluación de currículums con apoyo de inteligencia artificial.

## Tecnologías

- **Frontend:** React 19, Vite 8, React Router y Bootstrap 5.
- **Backend:** Java 21, Spring Boot 4.1.2, Maven y Spring Security (JWT).
- **Base de datos:** PostgreSQL.
- **IA:** servicio externo configurable mediante `OPENAI_API_KEY`.

## 1. Requisitos para ejecutar en otro computador

Instalar antes de comenzar:

- Git.
- **JDK 21** (`java -version`).
- **Maven** (`mvn -version`).
- **Node.js** compatible con Vite 8 (recomendado Node.js 22 LTS) y npm (`node -v`, `npm -v`).
- **PostgreSQL** en ejecución; pgAdmin es opcional.
- Conexión a Internet para instalar dependencias y usar la IA.

> Los comandos de este documento están preparados para **PowerShell en Windows**.

## 2. Descargar el proyecto

```powershell
git clone URL_DEL_REPOSITORIO
cd AquaChile-FULLSTACK
```

Reemplazar `URL_DEL_REPOSITORIO` por la dirección real de GitHub. Si la carpeta clonada tiene otro nombre, entrar en esa carpeta.

## 3. Crear la base de datos

En pgAdmin, conectado al servidor PostgreSQL, crear una base llamada **`aquachile_dev`**. Alternativamente, ejecutar en una base administrativa:

```sql
CREATE DATABASE aquachile_dev;
```

No ejecutar esta instrucción si ya existe. El backend está configurado para utilizar PostgreSQL; las tablas se gestionan con la configuración JPA de desarrollo. **No se requiere copiar la base de datos del computador original.**

## 4. Configurar e iniciar el backend

Abrir una terminal PowerShell desde la raíz del repositorio:

```powershell
cd .\aquachile-backend

$env:DB_URL = "jdbc:postgresql://localhost:5432/aquachile_dev"
$env:DB_USER = "postgres"
$env:DB_PASSWORD = "TU_CONTRASENA_POSTGRES"
$env:JWT_SECRET = "CAMBIA_ESTO_POR_UN_SECRETO_ALEATORIO_DE_32_O_MAS_CARACTERES"
$env:ADMIN_INITIAL_PASSWORD = "ELIGE_UNA_CONTRASENA_ADMIN_SEGURA"
$env:CORS_ORIGINS = "http://localhost:5173"
$env:UPLOAD_DIR = "./uploads"

mvn spring-boot:run
```

Cambiar los valores de ejemplo por los de ese computador. **No subir contraseñas reales a GitHub.** Las variables de PowerShell son temporales y solo aplican a esa terminal.

El backend debería quedar disponible en **http://localhost:8080**.

Comprobar en otra terminal:

```powershell
Invoke-RestMethod http://localhost:8080/api/health
```

### Cuenta inicial de administrador

Si la base está vacía, la aplicación puede crear la cuenta administrativa inicial con:

- **Correo:** `admin@aquachile.cl`
- **Contraseña:** la definida en `ADMIN_INITIAL_PASSWORD`.

La contraseña inicial se utiliza para crear la cuenta cuando todavía no existe; cambiarla después del primer acceso y no publicar su valor.

## 5. Iniciar el frontend

Abrir **otra** terminal PowerShell desde la raíz del repositorio:

```powershell
npm ci
npm run dev
```

Abrir **http://localhost:5173** e iniciar sesión como administrador. Mantener abiertas las terminales de backend y frontend.

## 6. Activar la evaluación con IA (opcional)

La aplicación permite configurar una clave de IA en el backend. **Antes de iniciar Spring Boot**, definir en su terminal:

```powershell
$env:OPENAI_API_KEY = "TU_CLAVE_PERSONAL"
$env:GROQ_MODEL="openai/gpt-oss-20b"
```

El proveedor y modelo deben coincidir con el servicio de IA realmente configurado en el backend. Sin una clave válida, el análisis externo puede no funcionar aunque las demás pantallas sí funcionen. Nunca subir claves de API al repositorio ni utilizar CV con datos personales reales en una demostracion, en este caso se usa GROQ.

## 7. Datos de ejemplo y demostración

El proyecto incluye **seis CV PDF ficticios** en `aquachile-backend/src/main/resources/seed-cvs/` para las pruebas. El código de inicialización debe estar presente y habilitado en la versión publicada para que se creen automáticamente las ofertas, postulantes y postulaciones al iniciar con una base vacía.

Para la presentación:

1. Iniciar sesión como **Administrador**.
2. Abrir **Candidatos y solicitudes** y comprobar los registros en PostgreSQL.
3. Abrir **Indicadores** y revisar los conteos.
4. Abrir **Evaluación de CV con IA** y seleccionar una postulación.
5. Pulsar **Analizar CV con IA** y mostrar el informe orientativo.
6. Descargar el CV original y comparar dos perfiles distintos.
7. Abrir **Solicitudes de acceso** y mostrar la revisión administrativa.

> El análisis de IA es orientativo y **no reemplaza la decisión humana**.

## 8. Verificaciones

En la raíz del proyecto:

```powershell
npm run build
npm run test:run
npm run lint
```

En `aquachile-backend`:

```powershell
mvn clean verify
```

**Nota:** `npm run lint` puede mostrar advertencias o errores heredados hasta que se corrijan; no debe interpretarse como una prueba superada sin revisar su salida. Las pruebas del backend requieren las dependencias y configuración correspondientes.

## 9. Problemas frecuentes

| Problema | Qué revisar |
|---|---|
| `Connection refused` de PostgreSQL | Que PostgreSQL esté iniciado, la base exista y usuario/contraseña sean correctos |
| Error de conexión a `localhost:8080` | Que Spring Boot esté ejecutándose y no haya errores al iniciar |
| `401 Unauthorized` | Volver a iniciar sesión y comprobar las credenciales |
| `403 Forbidden` | Verificar el rol y los permisos del endpoint |
| La IA no responde | Comprobar clave de API, conexión a Internet, proveedor y modelo |
| No aparecen CV de ejemplo | Verificar que `seed-cvs` y el inicializador de datos estén incluidos en GitHub |
| Puerto 5173 ocupado | Cerrar otro proceso de Vite; si cambia el puerto, actualizar `CORS_ORIGINS` |

## 10. Seguridad y archivos que no deben subirse

**No publicar** contraseñas, tokens JWT, claves de IA, archivos `.env` reales, bases de datos exportadas ni CV personales. Tampoco subir `node_modules/`, `dist/`, `target/`, `coverage/` ni archivos de `uploads/` con datos reales.

**Sí conservar** `package.json`, `package-lock.json`, `pom.xml`, `.gitignore`, `.env.example`, el código fuente y los PDF ficticios de `seed-cvs` necesarios para la presentación.

Antes de publicar, revisar `git status` y los archivos incluidos con `git diff --cached --name-only`.

---

**Proyecto académico AquaChile — entorno de desarrollo y demostración.**
