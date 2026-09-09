package datos;

import modelo.Animal;

public class AnimalDatos {

    private Animal[] animales;
    private int cantidad;

    public AnimalDatos(int capacidad) {
        animales = new Animal[capacidad];
        cantidad = 0;
    }

    public boolean registrarAnimal(Animal animal) {
        if (animal == null
                || textoVacio(animal.getCodigo())
                || textoVacio(animal.getNombre())
                || textoVacio(animal.getEspecie())
                || textoVacio(animal.getEstado())) {
            return false;
        }

        if (!estadoValido(animal.getEstado())
                || buscarAnimal(animal.getCodigo()) != null
                || cantidad >= animales.length) {
            return false;
        }

        animales[cantidad] = animal;
        cantidad++;
        return true;
    }

    public void listarAnimales() {
        if (cantidad == 0) {
            System.out.println("No hay animales registrados.");
            return;
        }

        boolean hayActivos = false;

        for (int i = 0; i < cantidad; i++) {
            if (!"Eliminado".equals(animales[i].getEstado())) {
                System.out.println(animales[i]);
                hayActivos = true;
            }
        }

        if (!hayActivos) {
            System.out.println("No hay animales registrados.");
        }
    }

    public Animal buscarAnimal(String codigo) {
        if (codigo == null) {
            return null;
        }

        for (int i = 0; i < cantidad; i++) {
            if (animales[i].getCodigo().equals(codigo)) {
                return animales[i];
            }
        }

        return null;
    }

    public boolean editarEstado(String codigo, String nuevoEstado) {
        if (!estadoValido(nuevoEstado)) {
            return false;
        }

        Animal animal = buscarAnimal(codigo);

        if (animal == null) {
            return false;
        }

        animal.setEstado(nuevoEstado);
        return true;
    }

    public boolean eliminarAnimal(String codigo) {
        Animal animal = buscarAnimal(codigo);

        if (animal == null || "Eliminado".equals(animal.getEstado())) {
            return false;
        }

        animal.setEstado("Eliminado");
        return true;
    }

    public boolean coincideTexto(String texto, String criterio) {
        if (texto == null || criterio == null) {
            return false;
        }

        return texto.toLowerCase().contains(criterio.toLowerCase());
    }

    public int getCantidad() {
        return cantidad;
    }

    public Animal getAnimal(int indice) {
        if (indice >= 0 && indice < cantidad) {
            return animales[indice];
        }

        return null;
    }

    public void limpiarDatos() {
        for (int i = 0; i < animales.length; i++) {
            animales[i] = null;
        }

        cantidad = 0;
    }

    private boolean textoVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    private boolean estadoValido(String estado) {
        return "Rescatado".equals(estado)
                || "En atención".equals(estado)
                || "Disponible".equals(estado)
                || "Adoptado".equals(estado)
                || "Eliminado".equals(estado);
    }
}
