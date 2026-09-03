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

    // Verificar que el objeto no sea nulo
    if (animal == null) {
        return false;
    }

    // Verificar que los datos principales no estén vacíos
    if (animal.getCodigo() == null || animal.getCodigo().trim().isEmpty()) {
        return false;
    }

    if (animal.getNombre() == null || animal.getNombre().trim().isEmpty()) {
        return false;
    }

    if (animal.getEspecie() == null || animal.getEspecie().trim().isEmpty()) {
        return false;
    }

    if (animal.getEstado() == null || animal.getEstado().trim().isEmpty()) {
        return false;
    }

    // Verificar código repetido
    if (buscarAnimal(animal.getCodigo()) != null) {
        return false;
    }

    // Verificar capacidad del arreglo
    if (cantidad >= animales.length) {
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

    boolean hayAnimales = false;

    for (int i = 0; i < cantidad; i++) {

        if (!animales[i].getEstado().equals("Eliminado")) {
            System.out.println(animales[i]);
            hayAnimales = true;
        }
    }

    if (!hayAnimales) {
        System.out.println("No hay animales registrados.");
    }
}
    
    public Animal buscarAnimal(String codigo) {

    for (int i = 0; i < cantidad; i++) {

        if (animales[i].getCodigo().equals(codigo)) {
            return animales[i];
        }
    }

    return null;
}
    
    public boolean editarEstado(String codigo, String nuevoEstado) {

    Animal animal = buscarAnimal(codigo);

    if (animal != null) {
        animal.setEstado(nuevoEstado);
        return true;
    }

    return false;
}
    
    public boolean eliminarAnimal(String codigo) {

    Animal animal = buscarAnimal(codigo);

    if (animal != null) {
        animal.setEstado("Eliminado");
        return true;
    }

    return false;
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
    
}