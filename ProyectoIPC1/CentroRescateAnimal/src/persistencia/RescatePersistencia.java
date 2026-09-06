package persistencia;

import datos.RescateDatos;
import modelo.Rescate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class RescatePersistencia {

    private String nombreArchivo;

    public RescatePersistencia(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public boolean guardar(RescateDatos datos) {

        try {

            PrintWriter escritor =
                    new PrintWriter(
                            new FileWriter(nombreArchivo)
                    );

            for (int i = 0; i < datos.getCantidad(); i++) {

                Rescate rescate =
                        datos.getRescate(i);

                escritor.println(
                        rescate.getCodigo() + ";"
                        + rescate.getFecha() + ";"
                        + rescate.getUbicacion() + ";"
                        + rescate.getEspecie() + ";"
                        + rescate.getDescripcion() + ";"
                        + rescate.getEstado()
                );
            }

            escritor.close();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error al guardar los rescates."
            );

            return false;
        }
    }

    public boolean cargar(RescateDatos datos) {

        try {

            BufferedReader lector =
                    new BufferedReader(
                            new FileReader(nombreArchivo)
                    );

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] partes =
                        linea.split(";");

                if (partes.length == 6) {

                    Rescate rescate =
                            new Rescate(
                                    partes[0],
                                    partes[1],
                                    partes[2],
                                    partes[3],
                                    partes[4],
                                    partes[5]
                            );

                    datos.registrarRescate(rescate);
                }
            }

            lector.close();

            return true;

        } catch (java.io.FileNotFoundException e) {

            System.out.println(
                    "El archivo de rescates todavía no existe."
            );

            return false;

        } catch (Exception e) {

            System.out.println(
                    "Error al cargar los rescates."
            );

            return false;
        }
    }
}