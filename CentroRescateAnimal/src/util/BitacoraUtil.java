package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BitacoraUtil {
    private static final String ARCHIVO = "bitacora.txt";
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private BitacoraUtil() { }

    public static void registrar(String usuario, String accion, String detalle) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(ARCHIVO, true))) {
            escritor.println(
                    LocalDateTime.now().format(FORMATO) + ";"
                    + limpiar(usuario) + ";"
                    + limpiar(accion) + ";"
                    + limpiar(detalle)
            );
        } catch (Exception e) {
            System.out.println("No se pudo registrar la bitácora.");
        }
    }

    public static String[] leer() {
        try (BufferedReader lector = new BufferedReader(new FileReader(ARCHIVO))) {
            String[] datos = new String[1000];
            int cantidad = 0;
            String linea;
            while ((linea = lector.readLine()) != null && cantidad < datos.length) {
                datos[cantidad++] = linea;
            }
            String[] resultado = new String[cantidad];
            for (int i = 0; i < cantidad; i++) resultado[i] = datos[i];
            return resultado;
        } catch (Exception e) {
            return new String[0];
        }
    }

    private static String limpiar(String texto) {
        if (texto == null) return "";
        return texto.replace(";", ",").replace("\n", " ").replace("\r", " ");
    }
}
