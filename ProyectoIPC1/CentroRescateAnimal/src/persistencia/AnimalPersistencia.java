package persistencia;

import datos.AnimalDatos;
import modelo.Animal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class AnimalPersistencia {

    private String nombreArchivo;

    public AnimalPersistencia(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public boolean guardar(AnimalDatos datos) {

        try {

            PrintWriter escritor = new PrintWriter(
                    new FileWriter(nombreArchivo)
            );

            for (int i = 0; i < datos.getCantidad(); i++) {

                Animal animal = datos.getAnimal(i);

                escritor.println(
                        animal.getCodigo() + ";"
                        + animal.getNombre() + ";"
                        + animal.getEspecie() + ";"
                        + animal.getEstado()
                );
            }

            escritor.close();

            return true;

        } catch (Exception e) {

            System.out.println("Error al guardar los animales.");
            return false;
        }
    }
    
    public boolean cargar(AnimalDatos datos) {

    try {

        BufferedReader lector = new BufferedReader(
                new FileReader(nombreArchivo)
        );

        String linea;

        while ((linea = lector.readLine()) != null) {

            String[] partes = linea.split(";");

            if (partes.length == 4) {

                Animal animal = new Animal(
                        partes[0],
                        partes[1],
                        partes[2],
                        partes[3]
                );

                datos.registrarAnimal(animal);
            }
        }

        lector.close();

        return true;

    } catch (Exception e) {

        System.out.println("No se pudo cargar el archivo.");
        return false;
    }
}
}