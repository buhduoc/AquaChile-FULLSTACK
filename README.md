# Evaluaciones psicolaborales — AquaChile

Prototipo web para gestionar **candidatos** y **solicitudes de evaluación psicolaboral**, con control de acceso por usuario, panel de indicadores y una página pública para pedir una cuenta.

> **Prototipo académico:** no tiene servidor ni base de datos. Todo se guarda en el `localStorage` del navegador, por lo que los datos son locales a cada navegador.

---

## Contenido

1. [Tecnologías](#tecnologías)
2. [Requisitos e instalación](#requisitos-e-instalación)
3. [Cuentas de prueba](#cuentas-de-prueba)
4. [Rutas de la aplicación](#rutas-de-la-aplicación)
5. [Solicitudes de acceso de ejemplo](#solicitudes-de-acceso-de-ejemplo)
6. [Estructura del proyecto](#estructura-del-proyecto)
7. [Pruebas](#pruebas)

---


## Tecnologías

| Herramienta | Uso |
|---|---|
| React 19 | Interfaz |
| React Router 7 | Navegación entre secciones |
| Bootstrap 5 | Estilos y componentes base |
| Vite | Servidor de desarrollo y compilación |
| Vitest + Testing Library | Pruebas automáticas |
| ESLint | Revisión de código |
| FormSubmit | Envío de correos sin servidor propio |

---

## Requisitos e instalación

Necesitas [Node.js](https://nodejs.org) (versión LTS reciente) y npm.

```bash
npm install
npm run dev
```

Vite mostrará una dirección local (normalmente `http://localhost:5173`).

---

## Cuentas de prueba

| Usuario | Contraseña | Rol |
|---|---|---|
| `admin` | `admin123` | Administrador |
| `jefe.rrhh` | `rrhh123` | Administrador |
| `psicologo` | `psico123` | Usuario |

---

## Rutas de la aplicación

| Ruta | Acceso | Descripción |
|---|---|---|
| `/` | Con sesión | Redirige a `/inicio` |
| `/inicio` | Con sesión | Resumen, calendario y estadística del día |
| `/indicadores` | Permiso `indicadores` | Indicadores y gráficos |
| `/candidatos` | Permiso `candidatos` | Gestión de candidatos |
| `/solicitudes` | Permiso `solicitudes` | Gestión de solicitudes de evaluación |
| `/cuentas` | Administrador | Solicitudes de acceso |
| `/usuarios` | Administrador | Usuarios, conexiones y notificaciones |
| `/perfil` | Con sesión | Perfil propio |
| `/opciones` | Con sesión | Apariencia y tamaño |
| `/solicitud` | Pública | Solicitar acceso (`?tab=enviar`) y consultar estado (`?tab=estado`) |

Sin sesión se muestra el login. Si una ruta no existe o el usuario no tiene permiso para verla, se vuelve a `/inicio`.

---

## Solicitudes de acceso de ejemplo

### Resumen de los datos

| Persona | Usuario | Estado | Correo | Código |
|---|---|---|---|---|
| Camila Fuentes | `cfuentes` | Aceptada | camila.fuentes@example.test | `CAM482` |
| Matías Araya | `maraya` | Aceptada | matias.araya@example.test | `MAT735` |
| Javiera Torres | `jtorres` | Rechazada | javiera.torres@example.test | `JAV296` |
| Ignacio Paredes | `iparedes` | Rechazada | ignacio.paredes@example.test | `IGN914` |
| Felipe Núñez | `fnunez` | Pendiente (con comentario del admin) | felipe.nunez@example.test | `FEL358` |
| Constanza Vera | `cvera` | Pendiente | constanza.vera@example.test | `CON672` |
| Andrea Muñoz | `amunoz` | Pendiente | andrea.munoz@example.test | `AND847` |

---

## Estructura del proyecto

```
├── index.html
├── package.json
├── vite.config.js
├── eslint.config.js
├── .env.example
├── public/
└── src/
    ├── main.jsx
    ├── App.jsx
    ├── auth.js
    ├── logica.js
    ├── fechas.js
    ├── correo.js
    ├── pdf.js
    ├── opciones.js
    ├── fondos.js
    ├── useAlmacenLocal.js
    ├── useDatosAcceso.js
    ├── usePresencia.js
    ├── pages/
    │   ├── LoginPage.jsx
    │   ├── DashboardPage.jsx
    │   └── SolicitudAccesoPage.jsx
    ├── components/
    └── assets/
```

---

## Pruebas

```bash
npm run test:run
```

Las pruebas cubren las validaciones y la lógica de negocio (`logica.test.js`), el sistema de acceso (`auth.test.js`), la presencia de usuarios (`presencia.test.js`) y los flujos principales de la interfaz (`App.test.jsx`): login, solicitudes de acceso, administración y permisos.
