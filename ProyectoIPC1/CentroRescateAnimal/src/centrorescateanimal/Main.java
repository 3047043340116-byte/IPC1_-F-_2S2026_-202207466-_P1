package centrorescateanimal;

import datos.AnimalDatos;
import modelo.Animal;
import persistencia.AnimalPersistencia;

public class Main {

    public static void main(String[] args) {

        AnimalDatos datosAnimales = new AnimalDatos(100);

        Animal animal1 = new Animal(
                "A001",
                "Firulais",
                "Perro",
                "Rescatado"
        );

        Animal animal2 = new Animal(
                "A002",
                "Michi",
                "Gato",
                "En tratamiento"
        );

        datosAnimales.registrarAnimal(animal1);
        datosAnimales.registrarAnimal(animal2);

        System.out.println("=== ANIMALES REGISTRADOS ===");

        datosAnimales.listarAnimales();
    
System.out.println("\n=== BUSCAR ANIMAL ===");

Animal encontrado = datosAnimales.buscarAnimal("A001");

if (encontrado != null) {
    System.out.println("Animal encontrado:");
    System.out.println(encontrado);
} else {
    System.out.println("Animal no encontrado.");
}    

System.out.println("\n=== EDITAR ESTADO ===");

boolean actualizado = datosAnimales.editarEstado(
        "A001",
        "En tratamiento"
);

if (actualizado) {
    System.out.println("Estado actualizado correctamente.");
} else {
    System.out.println("No se encontró el animal.");
}

System.out.println("\n=== ANIMALES DESPUÉS DE EDITAR ===");

datosAnimales.listarAnimales();


System.out.println("\n=== ELIMINAR ANIMAL ===");

boolean eliminado = datosAnimales.eliminarAnimal("A002");

if (eliminado) {
    System.out.println("Animal eliminado correctamente.");
} else {
    System.out.println("No se encontró el animal.");
}

System.out.println("\n=== ANIMALES DESPUÉS DE ELIMINAR ===");

datosAnimales.listarAnimales();

System.out.println("\n=== GUARDAR ANIMALES ===");

AnimalPersistencia persistencia =
        new AnimalPersistencia("animales.txt");

boolean guardado = persistencia.guardar(datosAnimales);

if (guardado) {
    System.out.println("Animales guardados correctamente.");
} else {
    System.out.println("No se pudieron guardar los animales.");
}

System.out.println("\n=== CARGAR ANIMALES ===");

AnimalDatos nuevosDatos = new AnimalDatos(100);

boolean cargado = persistencia.cargar(nuevosDatos);

if (cargado) {
    System.out.println("Animales cargados correctamente.");

    System.out.println("\n=== ANIMALES CARGADOS ===");

    nuevosDatos.listarAnimales();

} else {
    System.out.println("No se pudieron cargar los animales.");
}

    }
}