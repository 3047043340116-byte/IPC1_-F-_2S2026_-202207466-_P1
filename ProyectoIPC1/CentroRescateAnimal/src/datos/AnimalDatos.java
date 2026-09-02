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

        for (int i = 0; i < cantidad; i++) {
            System.out.println(animales[i]);
        }
    }
}