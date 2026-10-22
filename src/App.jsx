import { useState } from "react";
import OpcionesPanel from "./components/OpcionesPanel";
import { FONDOS } from "./fondos";
import { useOpciones } from "./opciones";
import AppNavbar from "./components/AppNavbar";
import Sidebar from "./components/Sidebar";
import DashboardPage from "./pages/DashboardPage";
import LoginPage from "./pages/LoginPage";

const ES_ESCRITORIO = "(min-width: 768px)";

function App() {

  const [usuario, setUsuario] = useState(null);
  const [vista, setVista] = useState("inicio");
  const [opciones, setOpciones] = useOpciones();
 
  const [sidebarAbierto, setSidebarAbierto] = useState(
    () => window.matchMedia(ES_ESCRITORIO).matches,
  );

  function cambiarVista(nuevaVista) {
    setVista(nuevaVista);
    cerrarEnMovil();
  }

  function cerrarEnMovil() {
    if (!window.matchMedia(ES_ESCRITORIO).matches) {
      setSidebarAbierto(false);
    }
  }

  if (!usuario) {
    return <LoginPage onLogin={setUsuario} />;
  }

  const fondo = FONDOS.find((f) => f.id === opciones.fondo);
  const escala = opciones.sistema / 100;

  return (
    <div
      className={`app-shell ${sidebarAbierto ? "sidebar-abierto" : "sidebar-cerrado"} ${fondo?.imagen ? "con-fondo" : ""}`}
      style={{
        zoom: escala,
        "--zoom-sistema": escala,
        "--fondo-imagen": fondo?.imagen ? `url("${fondo.imagen}")` : "none",
      }}
    >
      <AppNavbar
        sidebarAbierto={sidebarAbierto}
        onToggleSidebar={() => setSidebarAbierto((abierto) => !abierto)}
      />
      <Sidebar
        usuario={usuario.usuario}
        vista={vista}
        onCambiarVista={cambiarVista}
        onCerrarSesion={() => {
          setVista("inicio");
          setUsuario(null);
        }}
      />
      <div className="sidebar-backdrop" onClick={cerrarEnMovil} aria-hidden="true" />

      <div className="content-wrapper">
        {vista === "opciones" ? (
          <main className="contenido">
            <OpcionesPanel
              opciones={opciones}
              onCambiar={(cambios) => setOpciones((actuales) => ({ ...actuales, ...cambios }))}
            />
          </main>
        ) : (
          <DashboardPage vista={vista} esAdmin={usuario.rol === "Administrador"} />
        )}
      </div>

      <footer className="main-footer">
        Copyright © 2026 todos los derechos reservados.
      </footer>
    </div>
  );
}

export default App;
