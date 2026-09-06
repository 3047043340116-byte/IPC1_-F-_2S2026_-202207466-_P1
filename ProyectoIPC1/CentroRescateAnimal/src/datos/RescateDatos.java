package datos;

import modelo.Rescate;

public class RescateDatos {

    private Rescate[] rescates;
    private int cantidad;

    public RescateDatos(int capacidad) {

        rescates = new Rescate[capacidad];
        cantidad = 0;
    }

    // ==========================================
    // REGISTRAR RESCATE
    // ==========================================

    public boolean registrarRescate(Rescate rescate) {

        if (rescate == null) {
            return false;
        }

        if (rescate.getCodigo() == null
                || rescate.getCodigo().trim().isEmpty()) {
            return false;
        }

        if (rescate.getFecha() == null
                || rescate.getFecha().trim().isEmpty()) {
            return false;
        }

        if (rescate.getUbicacion() == null
                || rescate.getUbicacion().trim().isEmpty()) {
            return false;
        }

        if (rescate.getEspecie() == null
                || rescate.getEspecie().trim().isEmpty()) {
            return false;
        }

        if (rescate.getDescripcion() == null
                || rescate.getDescripcion().trim().isEmpty()) {
            return false;
        }

        if (!estadoValido(rescate.getEstado())) {
            return false;
        }

        // Evitar códigos repetidos
        if (buscarRescate(rescate.getCodigo()) != null) {
            return false;
        }

        // Verificar capacidad
        if (cantidad >= rescates.length) {
            return false;
        }

        rescates[cantidad] = rescate;
        cantidad++;

        return true;
    }

    // ==========================================
    // LISTAR RESCATES
    // ==========================================

    public void listarRescates() {

        if (cantidad == 0) {

            System.out.println(
                    "No hay rescates registrados."
            );

            return;
        }

        for (int i = 0; i < cantidad; i++) {

            if (!rescates[i].getEstado().equals("Eliminado")) {

                System.out.println(
                        rescates[i]
                );
            }
        }
    }

    // ==========================================
    // BUSCAR RESCATE
    // ==========================================

    public Rescate buscarRescate(String codigo) {

        for (int i = 0; i < cantidad; i++) {

            if (rescates[i].getCodigo().equals(codigo)) {

                return rescates[i];
            }
        }

        return null;
    }

    // ==========================================
    // CAMBIAR ESTADO
    // ==========================================

    public boolean cambiarEstado(
            String codigo,
            String nuevoEstado) {

        if (!estadoValido(nuevoEstado)) {
            return false;
        }

        Rescate rescate =
                buscarRescate(codigo);

        if (rescate == null) {
            return false;
        }

        rescate.setEstado(nuevoEstado);

        return true;
    }

    // ==========================================
    // ELIMINAR RESCATE
    // ==========================================

    public boolean eliminarRescate(String codigo) {

        Rescate rescate =
                buscarRescate(codigo);

        if (rescate == null) {
            return false;
        }

        rescate.setEstado("Eliminado");

        return true;
    }

    // ==========================================
    // VALIDAR ESTADO
    // ==========================================

    private boolean estadoValido(String estado) {

        if (estado == null) {
            return false;
        }

        return estado.equals("Registrado")
                || estado.equals("En atención")
                || estado.equals("Completado")
                || estado.equals("Eliminado");
    }

    // ==========================================
    // CANTIDAD
    // ==========================================

    public int getCantidad() {

        return cantidad;
    }

    // ==========================================
    // OBTENER RESCATE
    // ==========================================

    public Rescate getRescate(int indice) {

        if (indice >= 0
                && indice < cantidad) {

            return rescates[indice];
        }

        return null;
    }
}