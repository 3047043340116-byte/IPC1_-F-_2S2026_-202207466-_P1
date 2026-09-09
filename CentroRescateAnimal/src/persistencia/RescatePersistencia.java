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
        try (PrintWriter escritor =
                new PrintWriter(new FileWriter(nombreArchivo))) {

            for (int i = 0; i < datos.getCantidad(); i++) {
                Rescate rescate = datos.getRescate(i);

                escritor.println(
                        rescate.getCodigo() + ";"
                        + rescate.getFecha() + ";"
                        + rescate.getUbicacion() + ";"
                        + rescate.getEspecie() + ";"
                        + rescate.getDescripcion() + ";"
                        + rescate.getPrioridad() + ";"
                        + rescate.getEstado()
                );
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean cargar(RescateDatos datos) {
        try (BufferedReader lector =
                new BufferedReader(new FileReader(nombreArchivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";", -1);

                if (partes.length == 7) {
                    datos.registrarRescate(
                            new Rescate(
                                    partes[0],
                                    partes[1],
                                    partes[2],
                                    partes[3],
                                    partes[4],
                                    partes[5],
                                    partes[6]
                            )
                    );
                } else if (partes.length == 6) {
                    // Permite cargar archivos creados antes de agregar prioridad.
                    datos.registrarRescate(
                            new Rescate(
                                    partes[0],
                                    partes[1],
                                    partes[2],
                                    partes[3],
                                    partes[4],
                                    "Media",
                                    partes[5]
                            )
                    );
                }
            }

            return true;

        } catch (java.io.FileNotFoundException e) {
            return false;

        } catch (Exception e) {
            return false;
        }
    }
}
