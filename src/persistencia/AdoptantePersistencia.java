package persistencia;

import datos.AdoptanteDatos;
import modelo.Adoptante;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class AdoptantePersistencia {

    private String nombreArchivo;

    public AdoptantePersistencia(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public boolean guardar(AdoptanteDatos datos) {

        try {

            PrintWriter escritor = new PrintWriter(
                    new FileWriter(nombreArchivo)
            );

            for (int i = 0; i < datos.getCantidad(); i++) {

                Adoptante adoptante = datos.getAdoptante(i);

                escritor.println(
                        adoptante.getDpi() + ";"
                        + adoptante.getNombre() + ";"
                        + adoptante.getTelefono() + ";"
                        + adoptante.getDireccion() + ";"
                        + adoptante.getEstado()
                );
            }

            escritor.close();

            return true;

        } catch (Exception e) {

            System.out.println("Error al guardar los adoptantes.");
            return false;
        }
    }

    public boolean cargar(AdoptanteDatos datos) {

        try {

            BufferedReader lector = new BufferedReader(
                    new FileReader(nombreArchivo)
            );

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] partes = linea.split(";");

                if (partes.length == 5) {

                    Adoptante adoptante = new Adoptante(
                            partes[0],
                            partes[1],
                            partes[2],
                            partes[3],
                            partes[4]
                    );

                    datos.registrarAdoptante(adoptante);
                }
            }

            lector.close();

            return true;

        } catch (java.io.FileNotFoundException e) {

            System.out.println("El archivo todavía no existe.");
            return false;

        } catch (Exception e) {

            System.out.println("Error al cargar los adoptantes.");
            return false;
        }
    }
}