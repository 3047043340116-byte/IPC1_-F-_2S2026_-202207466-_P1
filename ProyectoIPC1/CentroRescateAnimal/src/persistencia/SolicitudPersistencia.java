package persistencia;

import datos.SolicitudDatos;
import modelo.Solicitud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class SolicitudPersistencia {

    private String nombreArchivo;

    public SolicitudPersistencia(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public boolean guardar(SolicitudDatos datos) {

        try {

            PrintWriter escritor =
                    new PrintWriter(
                            new FileWriter(nombreArchivo)
                    );

            for (int i = 0; i < datos.getCantidad(); i++) {

                Solicitud solicitud =
                        datos.getSolicitud(i);

                escritor.println(
                        solicitud.getCodigo() + ";"
                        + solicitud.getDpiAdoptante() + ";"
                        + solicitud.getCodigoAnimal() + ";"
                        + solicitud.getFecha() + ";"
                        + solicitud.getEstado()
                );
            }

            escritor.close();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error al guardar las solicitudes."
            );

            return false;
        }
    }

    public boolean cargar(SolicitudDatos datos) {

        try {

            BufferedReader lector =
                    new BufferedReader(
                            new FileReader(nombreArchivo)
                    );

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] partes =
                        linea.split(";");

                if (partes.length == 5) {

                    Solicitud solicitud =
                            new Solicitud(
                                    partes[0],
                                    partes[1],
                                    partes[2],
                                    partes[3],
                                    partes[4]
                            );

                    datos.registrarSolicitud(solicitud);
                }
            }

            lector.close();

            return true;

        } catch (java.io.FileNotFoundException e) {

            System.out.println(
                    "El archivo de solicitudes todavía no existe."
            );

            return false;

        } catch (Exception e) {

            System.out.println(
                    "Error al cargar las solicitudes."
            );

            return false;
        }
    }
}