package persistencia;

import datos.UsuarioDatos;
import modelo.Usuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class UsuarioPersistencia {

    private String nombreArchivo;

    public UsuarioPersistencia(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public boolean guardar(UsuarioDatos datos) {
        try (PrintWriter escritor =
                new PrintWriter(new FileWriter(nombreArchivo))) {

            for (int i = 0; i < datos.getCantidad(); i++) {
                Usuario usuario = datos.getUsuario(i);

                escritor.println(
                        usuario.getUsuario() + ";"
                        + usuario.getContrasena() + ";"
                        + usuario.getRol()
                );
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean cargar(UsuarioDatos datos) {
        try (BufferedReader lector =
                new BufferedReader(new FileReader(nombreArchivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";", -1);

                if (partes.length == 3) {
                    datos.registrarUsuario(
                            new Usuario(
                                    partes[0].trim(),
                                    partes[1],
                                    partes[2].trim()
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
