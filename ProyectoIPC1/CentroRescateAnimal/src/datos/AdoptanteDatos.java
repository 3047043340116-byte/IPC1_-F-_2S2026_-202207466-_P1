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

    if (adoptante == null) {
        return false;
    }

    // Validar DPI
    if (adoptante.getDpi() == null
            || adoptante.getDpi().trim().isEmpty()) {
        return false;
    }

    if (adoptante.getDpi().length() != 13) {
        return false;
    }

    if (!adoptante.getDpi().matches("\\d+")) {
        return false;
    }

    // Validar nombre
    if (adoptante.getNombre() == null
            || adoptante.getNombre().trim().isEmpty()) {
        return false;
    }

    // Validar teléfono
    if (adoptante.getTelefono() == null
            || adoptante.getTelefono().trim().isEmpty()) {
        return false;
    }

    // Validar dirección
    if (adoptante.getDireccion() == null
            || adoptante.getDireccion().trim().isEmpty()) {
        return false;
    }

    // Evitar DPI repetido
    if (buscarAdoptante(adoptante.getDpi()) != null) {
        return false;
    }

    // Verificar capacidad
    if (cantidad >= adoptantes.length) {
        return false;
    }

    adoptantes[cantidad] = adoptante;
    cantidad++;

    return true;
}

    public void listarAdoptantes() {

        if (cantidad == 0) {
            System.out.println("No hay adoptantes registrados.");
            return;
        }

        boolean hayAdoptantes = false;

        for (int i = 0; i < cantidad; i++) {

            if (!adoptantes[i].getEstado().equals("Eliminado")) {

                System.out.println(adoptantes[i]);
                hayAdoptantes = true;
            }
        }

        if (!hayAdoptantes) {
            System.out.println("No hay adoptantes registrados.");
        }
    }

    public Adoptante buscarAdoptante(String dpi) {

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
            && !adoptante.getEstado().equals("Eliminado")) {
        return adoptante;
    }

    return null;
}

    public boolean editarAdoptante(String dpi, String nombre,
                                   String telefono, String direccion) {

        Adoptante adoptante = buscarAdoptante(dpi);

        if (adoptante != null) {

            adoptante.setNombre(nombre);
            adoptante.setTelefono(telefono);
            adoptante.setDireccion(direccion);

            return true;
        }

        return false;
    }

    public boolean eliminarAdoptante(String dpi) {

        Adoptante adoptante = buscarAdoptante(dpi);

        if (adoptante != null) {

            adoptante.setEstado("Eliminado");
            return true;
        }

        return false;
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
}