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

    public boolean registrarSolicitud(Solicitud solicitud) {
        if (solicitud == null
                || textoVacio(solicitud.getCodigo())
                || textoVacio(solicitud.getDpiAdoptante())
                || textoVacio(solicitud.getCodigoAnimal())
                || textoVacio(solicitud.getFecha())
                || !estadoValido(solicitud.getEstado())
                || buscarSolicitud(solicitud.getCodigo()) != null
                || cantidad >= solicitudes.length) {
            return false;
        }

        Adoptante adoptante =
                datosAdoptantes.buscarAdoptanteActivo(
                        solicitud.getDpiAdoptante());

        if (adoptante == null) {
            return false;
        }

        Animal animal =
                datosAnimales.buscarAnimal(
                        solicitud.getCodigoAnimal());

        if (animal == null
                || "Eliminado".equals(animal.getEstado())
                || "Adoptado".equals(animal.getEstado())) {
            return false;
        }

        if (tieneSolicitudActiva(solicitud.getCodigoAnimal())) {
            return false;
        }

        solicitudes[cantidad] = solicitud;
        cantidad++;
        return true;
    }

    public void listarSolicitudes() {
        if (cantidad == 0) {
            System.out.println("No hay solicitudes registradas.");
            return;
        }

        for (int i = 0; i < cantidad; i++) {
            System.out.println(solicitudes[i]);
        }
    }

    public Solicitud buscarSolicitud(String codigo) {
        if (codigo == null) {
            return null;
        }

        for (int i = 0; i < cantidad; i++) {
            if (solicitudes[i].getCodigo().equals(codigo)) {
                return solicitudes[i];
            }
        }

        return null;
    }

    public boolean cambiarEstado(String codigo, String nuevoEstado) {
        if (!estadoValido(nuevoEstado)) {
            return false;
        }

        Solicitud solicitud = buscarSolicitud(codigo);

        if (solicitud == null) {
            return false;
        }

        if ("Aprobada".equals(nuevoEstado)) {
            Animal animal =
                    datosAnimales.buscarAnimal(
                            solicitud.getCodigoAnimal());

            if (animal == null
                    || "Adoptado".equals(animal.getEstado())
                    || "Eliminado".equals(animal.getEstado())) {
                return false;
            }

            if (!datosAnimales.editarEstado(
                    solicitud.getCodigoAnimal(), "Adoptado")) {
                return false;
            }
        }

        solicitud.setEstado(nuevoEstado);
        return true;
    }

    public boolean atenderSolicitud(String codigo, String estadoFinal) {
        if (!"Aprobada".equals(estadoFinal)
                && !"Rechazada".equals(estadoFinal)) {
            return false;
        }

        return cambiarEstado(codigo, estadoFinal);
    }

    private boolean estadoValido(String estado) {
        return "Pendiente".equals(estado)
                || "Aprobada".equals(estado)
                || "Rechazada".equals(estado);
    }

    private boolean tieneSolicitudActiva(String codigoAnimal) {
        for (int i = 0; i < cantidad; i++) {
            Solicitud solicitud = solicitudes[i];

            if (solicitud.getCodigoAnimal().equals(codigoAnimal)
                    && ("Pendiente".equals(solicitud.getEstado())
                    || "Aprobada".equals(solicitud.getEstado()))) {
                return true;
            }
        }

        return false;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Solicitud getSolicitud(int indice) {
        if (indice >= 0 && indice < cantidad) {
            return solicitudes[indice];
        }

        return null;
    }

    private boolean textoVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
