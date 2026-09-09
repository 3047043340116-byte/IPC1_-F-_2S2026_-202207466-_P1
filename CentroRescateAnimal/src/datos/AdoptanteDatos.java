package datos;

import modelo.Adoptante;

public class AdoptanteDatos {

    private Adoptante[] adoptantes;
    private int cantidad;

    public AdoptanteDatos(int capacidad) {
        adoptantes = new Adoptante[capacidad];
        cantidad = 0;
    }

    public boolean registrarAdoptante(Adoptante adoptante) {
        if (!datosValidos(adoptante)
                || buscarAdoptante(adoptante.getDpi()) != null
                || cantidad >= adoptantes.length) {
            return false;
        }

        adoptantes[cantidad] = adoptante;
        cantidad++;
        return true;
    }

    private boolean datosValidos(Adoptante adoptante) {
        if (adoptante == null
                || textoVacio(adoptante.getDpi())
                || textoVacio(adoptante.getNombre())
                || textoVacio(adoptante.getTelefono())
                || textoVacio(adoptante.getDireccion())
                || textoVacio(adoptante.getEstado())) {
            return false;
        }

        return adoptante.getDpi().length() == 13
                && adoptante.getDpi().matches("\\d+")
                && estadoValido(adoptante.getEstado());
    }

    public void listarAdoptantes() {
        if (cantidad == 0) {
            System.out.println("No hay adoptantes registrados.");
            return;
        }

        boolean hayActivos = false;

        for (int i = 0; i < cantidad; i++) {
            if (!"Eliminado".equals(adoptantes[i].getEstado())) {
                System.out.println(adoptantes[i]);
                hayActivos = true;
            }
        }

        if (!hayActivos) {
            System.out.println("No hay adoptantes registrados.");
        }
    }

    public Adoptante buscarAdoptante(String dpi) {
        if (dpi == null) {
            return null;
        }

        for (int i = 0; i < cantidad; i++) {
            if (adoptantes[i].getDpi().equals(dpi)) {
                return adoptantes[i];
            }
        }

        return null;
    }

    public Adoptante buscarAdoptanteActivo(String dpi) {
        Adoptante adoptante = buscarAdoptante(dpi);

        if (adoptante != null
                && !"Eliminado".equals(adoptante.getEstado())
                && "Activo".equals(adoptante.getEstado())) {
            return adoptante;
        }

        return null;
    }

    public boolean editarAdoptante(String dpi, String nombre,
            String telefono, String direccion) {

        Adoptante adoptante = buscarAdoptanteActivo(dpi);

        if (adoptante == null
                || textoVacio(nombre)
                || textoVacio(telefono)
                || textoVacio(direccion)) {
            return false;
        }

        adoptante.setNombre(nombre);
        adoptante.setTelefono(telefono);
        adoptante.setDireccion(direccion);
        return true;
    }

    public boolean cambiarEstado(String dpi, String estado) {
        if (!estadoValido(estado)) {
            return false;
        }

        Adoptante adoptante = buscarAdoptante(dpi);

        if (adoptante == null) {
            return false;
        }

        adoptante.setEstado(estado);
        return true;
    }

    public boolean eliminarAdoptante(String dpi) {
        Adoptante adoptante = buscarAdoptante(dpi);

        if (adoptante == null || "Eliminado".equals(adoptante.getEstado())) {
            return false;
        }

        adoptante.setEstado("Eliminado");
        return true;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Adoptante getAdoptante(int indice) {
        if (indice >= 0 && indice < cantidad) {
            return adoptantes[indice];
        }

        return null;
    }

    private boolean textoVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    private boolean estadoValido(String estado) {
        return "Activo".equals(estado)
                || "Inactivo".equals(estado)
                || "Eliminado".equals(estado);
    }
}
