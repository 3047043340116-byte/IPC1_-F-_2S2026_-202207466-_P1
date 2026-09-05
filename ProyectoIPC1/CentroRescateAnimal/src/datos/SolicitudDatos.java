package datos;

import modelo.Solicitud;
import modelo.Animal;
import modelo.Adoptante;

public class SolicitudDatos {

    private Solicitud[] solicitudes;
    private int cantidad;

    private AnimalDatos datosAnimales;
    private AdoptanteDatos datosAdoptantes;

    public SolicitudDatos(int capacidad,
                          AnimalDatos datosAnimales,
                          AdoptanteDatos datosAdoptantes) {

        solicitudes = new Solicitud[capacidad];
        cantidad = 0;

        this.datosAnimales = datosAnimales;
        this.datosAdoptantes = datosAdoptantes;
    }

    // ==============================
    // REGISTRAR SOLICITUD
    // ==============================

    public boolean registrarSolicitud(Solicitud solicitud) {

        if (solicitud == null) {
            return false;
        }

        if (solicitud.getCodigo() == null
                || solicitud.getCodigo().trim().isEmpty()) {
            return false;
        }

        if (solicitud.getDpiAdoptante() == null
                || solicitud.getDpiAdoptante().trim().isEmpty()) {
            return false;
        }

        if (solicitud.getCodigoAnimal() == null
                || solicitud.getCodigoAnimal().trim().isEmpty()) {
            return false;
        }

        // Verificar que el código de solicitud no esté repetido
        if (buscarSolicitud(solicitud.getCodigo()) != null) {
            return false;
        }

        // Verificar que el estado sea válido
        if (!estadoValido(solicitud.getEstado())) {
            return false;
        }

        // Verificar que el adoptante exista y esté activo
        Adoptante adoptante =
                datosAdoptantes.buscarAdoptanteActivo(
                        solicitud.getDpiAdoptante()
                );

        if (adoptante == null) {
            return false;
        }

        // Verificar que el animal exista
        Animal animal =
                datosAnimales.buscarAnimal(
                        solicitud.getCodigoAnimal()
                );

        if (animal == null) {
            return false;
        }

        // Verificar que el animal no esté eliminado
        if (animal.getEstado().equals("Eliminado")) {
            return false;
        }

        // Verificar que el animal no tenga otra solicitud activa
        if (tieneSolicitudActiva(solicitud.getCodigoAnimal())) {
            return false;
        }

        // Verificar que todavía haya espacio
        if (cantidad >= solicitudes.length) {
            return false;
        }

        // Registrar solicitud
        solicitudes[cantidad] = solicitud;
        cantidad++;

        return true;
    }

    // ==============================
    // LISTAR SOLICITUDES
    // ==============================

    public void listarSolicitudes() {

        if (cantidad == 0) {
            System.out.println("No hay solicitudes registradas.");
            return;
        }

        for (int i = 0; i < cantidad; i++) {
            System.out.println(solicitudes[i]);
        }
    }

    // ==============================
    // BUSCAR SOLICITUD
    // ==============================

    public Solicitud buscarSolicitud(String codigo) {

        for (int i = 0; i < cantidad; i++) {

            if (solicitudes[i].getCodigo().equals(codigo)) {
                return solicitudes[i];
            }
        }

        return null;
    }

    // ==============================
    // CAMBIAR ESTADO
    // ==============================

    public boolean cambiarEstado(String codigo, String nuevoEstado) {

        if (!estadoValido(nuevoEstado)) {
            return false;
        }

        Solicitud solicitud = buscarSolicitud(codigo);

        if (solicitud == null) {
            return false;
        }

        solicitud.setEstado(nuevoEstado);

        // Si la solicitud fue aprobada,
        // cambiar el estado del animal.
        if (nuevoEstado.equals("Aprobada")) {

            datosAnimales.editarEstado(
                    solicitud.getCodigoAnimal(),
                    "Adoptado"
            );
        }

        return true;
    }

    // ==============================
    // VERIFICAR ESTADO
    // ==============================

    private boolean estadoValido(String estado) {

        if (estado == null) {
            return false;
        }

        return estado.equals("Pendiente")
                || estado.equals("Aprobada")
                || estado.equals("Rechazada");
    }

    // ==============================
    // VERIFICAR SOLICITUD ACTIVA
    // ==============================

    private boolean tieneSolicitudActiva(String codigoAnimal) {

        for (int i = 0; i < cantidad; i++) {

            Solicitud solicitud = solicitudes[i];

            if (solicitud.getCodigoAnimal().equals(codigoAnimal)) {

                if (solicitud.getEstado().equals("Pendiente")
                        || solicitud.getEstado().equals("Aprobada")) {

                    return true;
                }
            }
        }

        return false;
    }

    // ==============================
    // CANTIDAD
    // ==============================

    public int getCantidad() {
        return cantidad;
    }

    // ==============================
    // OBTENER SOLICITUD
    // ==============================

    public Solicitud getSolicitud(int indice) {

        if (indice >= 0 && indice < cantidad) {
            return solicitudes[indice];
        }

        return null;
    }
}