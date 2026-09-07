package persistencia;

import datos.EspacioRefugioDatos;
import modelo.EspacioRefugio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class EspacioRefugioPersistencia {

    private String nombreArchivo;

    public EspacioRefugioPersistencia(
            String nombreArchivo) {

        this.nombreArchivo = nombreArchivo;
    }

    public boolean guardar(
            EspacioRefugioDatos datos) {

        try {

            PrintWriter escritor =
                    new PrintWriter(
                            new FileWriter(nombreArchivo)
                    );

            for (int i = 0;
                    i < datos.getFilas();
                    i++) {

                for (int j = 0;
                        j < datos.getColumnas();
                        j++) {

                    EspacioRefugio espacio =
                            datos.consultarEspacio(i, j);

                    escritor.println(
                            i + ";"
                            + j + ";"
                            + espacio.getCodigoAnimal()
                    );
                }
            }

            escritor.close();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error al guardar los espacios."
            );

            return false;
        }
    }

    public boolean cargar(
            EspacioRefugioDatos datos) {

        try {

            BufferedReader lector =
                    new BufferedReader(
                            new FileReader(nombreArchivo)
                    );

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] partes =
                        linea.split(";");

                if (partes.length == 3) {

                    int fila =
                            Integer.parseInt(partes[0]);

                    int columna =
                            Integer.parseInt(partes[1]);

                    String codigoAnimal =
                            partes[2];

                    if (codigoAnimal != null
                            && !codigoAnimal.isEmpty()) {

                        datos.asignarAnimal(
                                fila,
                                columna,
                                codigoAnimal
                        );
                    }
                }
            }

            lector.close();

            return true;

        } catch (java.io.FileNotFoundException e) {

            System.out.println(
                    "El archivo de espacios todavía no existe."
            );

            return false;

        } catch (Exception e) {

            System.out.println(
                    "Error al cargar los espacios."
            );

            return false;
        }
    }
}