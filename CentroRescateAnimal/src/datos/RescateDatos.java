package datos;

import modelo.Rescate;

public class RescateDatos {

    private Rescate[] rescates;
    private int cantidad;

    public RescateDatos(int capacidad) {
        rescates = new Rescate[capacidad];
        cantidad = 0;
    }

    public boolean registrarRescate(Rescate rescate) {
        if (rescate == null
                || textoVacio(rescate.getCodigo())
                || textoVacio(rescate.getFecha())
                || textoVacio(rescate.getUbicacion())
                || textoVacio(rescate.getEspecie())
                || textoVacio(rescate.getDescripcion())
                || !prioridadValida(rescate.getPrioridad())
                || !estadoValido(rescate.getEstado())
                || buscarRescate(rescate.getCodigo()) != null
                || cantidad >= rescates.length) {
            return false;
        }

        rescates[cantidad] = rescate;
        cantidad++;
        return true;
    }

    public void listarRescates() {
        if (cantidad == 0) {
            System.out.println("No hay rescates registrados.");
            return;
        }

        boolean hayActivos = false;

        for (int i = 0; i < cantidad; i++) {
            if (!"Eliminado".equals(rescates[i].getEstado())) {
                System.out.println(rescates[i]);
                hayActivos = true;
            }
        }

        if (!hayActivos) {
            System.out.println("No hay rescates registrados.");
        }
    }

    public Rescate buscarRescate(String codigo) {
        if (codigo == null) {
            return null;
        }

        for (int i = 0; i < cantidad; i++) {
            if (rescates[i].getCodigo().equals(codigo)) {
                return rescates[i];
            }
        }

        return null;
    }

    public boolean cambiarEstado(String codigo, String nuevoEstado) {
        if (!estadoValido(nuevoEstado)) {
            return false;
        }

        Rescate rescate = buscarRescate(codigo);

        if (rescate == null || "Eliminado".equals(rescate.getEstado())) {
            return false;
        }

        rescate.setEstado(nuevoEstado);
        return true;
    }

    public boolean cambiarPrioridad(String codigo, String nuevaPrioridad) {
        if (!prioridadValida(nuevaPrioridad)) {
            return false;
        }

        Rescate rescate = buscarRescate(codigo);

        if (rescate == null || "Eliminado".equals(rescate.getEstado())) {
            return false;
        }

        rescate.setPrioridad(nuevaPrioridad);
        return true;
    }

    public boolean eliminarRescate(String codigo) {
        Rescate rescate = buscarRescate(codigo);

        if (rescate == null || "Eliminado".equals(rescate.getEstado())) {
            return false;
        }

        rescate.setEstado("Eliminado");
        return true;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Rescate getRescate(int indice) {
        if (indice >= 0 && indice < cantidad) {
            return rescates[indice];
        }

        return null;
    }

    private boolean textoVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    private boolean prioridadValida(String prioridad) {
        return "Alta".equals(prioridad)
                || "Media".equals(prioridad)
                || "Baja".equals(prioridad);
    }

    private boolean estadoValido(String estado) {
        return "Registrado".equals(estado)
                || "En atención".equals(estado)
                || "Completado".equals(estado)
                || "Eliminado".equals(estado);
    }
}
