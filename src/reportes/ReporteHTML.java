package reportes;

import datos.AnimalDatos;
import datos.AdoptanteDatos;
import datos.EspacioRefugioDatos;
import datos.SolicitudDatos;
import modelo.Animal;
import modelo.Adoptante;
import modelo.EspacioRefugio;
import modelo.Solicitud;
import persistencia.AnimalPersistencia;
import persistencia.AdoptantePersistencia;
import persistencia.EspacioRefugioPersistencia;
import persistencia.SolicitudPersistencia;
import util.BitacoraUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReporteHTML {
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private static String inicio(String titulo) {
        return "<!DOCTYPE html><html lang='es'><head><meta charset='UTF-8'>"
                + "<title>" + titulo + "</title><style>"
                + "body{font-family:Arial;margin:30px;background:#f5f5f5;}"
                + "h1{color:#222;}table{border-collapse:collapse;width:100%;background:white;}"
                + "th,td{border:1px solid #999;padding:8px;text-align:left;}"
                + "th{background:#ddd;}p{font-size:14px;}"
                + "</style></head><body><h1>" + titulo + "</h1>"
                + "<p>Generado: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "</p>";
    }

    private static String fin() { return "</body></html>"; }

    private static String nombre(String base) {
        return base + "_" + LocalDateTime.now().format(FORMATO) + ".html";
    }

    private static boolean escribir(String archivo, String contenido) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(archivo))) {
            escritor.print(contenido);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String generarAnimales() {
        AnimalDatos datos = new AnimalDatos(100);
        new AnimalPersistencia("animales.txt").cargar(datos);
        String archivo = nombre("reporte_animales");
        StringBuilder html = new StringBuilder(inicio("Reporte de animales"));
        html.append("<table><tr><th>Código</th><th>Nombre</th><th>Especie</th><th>Estado</th></tr>");
        for (int i = 0; i < datos.getCantidad(); i++) {
            Animal a = datos.getAnimal(i);
            if (a != null) html.append("<tr><td>").append(esc(a.getCodigo())).append("</td><td>")
                    .append(esc(a.getNombre())).append("</td><td>").append(esc(a.getEspecie()))
                    .append("</td><td>").append(esc(a.getEstado())).append("</td></tr>");
        }
        html.append("</table>").append(fin());
        if (escribir(archivo, html.toString())) {
            BitacoraUtil.registrar("Sistema", "REPORTE", "Generado reporte de animales");
            return new File(archivo).getAbsolutePath();
        }
        return null;
    }

    public static String generarAdopciones() {
        AnimalDatos animales = new AnimalDatos(100);
        AdoptanteDatos adoptantes = new AdoptanteDatos(100);
        new AnimalPersistencia("animales.txt").cargar(animales);
        new AdoptantePersistencia("adoptantes.txt").cargar(adoptantes);
        SolicitudDatos solicitudes = new SolicitudDatos(100, animales, adoptantes);
        new SolicitudPersistencia("solicitudes.txt").cargar(solicitudes);

        String archivo = nombre("reporte_adopciones");
        StringBuilder html = new StringBuilder(inicio("Reporte de adopciones"));
        html.append("<table><tr><th>Solicitud</th><th>DPI</th><th>Animal</th><th>Fecha</th><th>Estado</th></tr>");
        for (int i = 0; i < solicitudes.getCantidad(); i++) {
            Solicitud s = solicitudes.getSolicitud(i);
            html.append("<tr><td>").append(esc(s.getCodigo())).append("</td><td>")
                    .append(esc(s.getDpiAdoptante())).append("</td><td>")
                    .append(esc(s.getCodigoAnimal())).append("</td><td>")
                    .append(esc(s.getFecha())).append("</td><td>")
                    .append(esc(s.getEstado())).append("</td></tr>");
        }
        html.append("</table>").append(fin());
        if (escribir(archivo, html.toString())) {
            BitacoraUtil.registrar("Sistema", "REPORTE", "Generado reporte de adopciones");
            return new File(archivo).getAbsolutePath();
        }
        return null;
    }

    public static String generarOcupacion() {
        EspacioRefugioDatos datos = new EspacioRefugioDatos(4, 5);
        new EspacioRefugioPersistencia("espacios.txt").cargar(datos);
        String archivo = nombre("reporte_ocupacion");
        String[] areas = {"Recepción", "Cuarentena", "Recuperación", "Adopción"};
        StringBuilder html = new StringBuilder(inicio("Reporte de ocupación del refugio"));
        html.append("<p>Capacidad total: 20 espacios | Ocupados: ")
                .append(datos.contarOcupados()).append(" | Disponibles: ")
                .append(datos.contarDisponibles()).append("</p>");
        html.append("<table><tr><th>Área</th><th>Espacio</th><th>Estado</th><th>Código Animal</th></tr>");
        for (int i = 0; i < datos.getFilas(); i++) {
            for (int j = 0; j < datos.getColumnas(); j++) {
                EspacioRefugio e = datos.consultarEspacio(i, j);
                html.append("<tr><td>").append(areas[i]).append("</td><td>")
                        .append(j + 1).append("</td><td>")
                        .append(e.estaDisponible() ? "Disponible" : "Ocupado")
                        .append("</td><td>").append(esc(e.getCodigoAnimal())).append("</td></tr>");
            }
        }
        html.append("</table>").append(fin());
        if (escribir(archivo, html.toString())) {
            BitacoraUtil.registrar("Sistema", "REPORTE", "Generado reporte de ocupación");
            return new File(archivo).getAbsolutePath();
        }
        return null;
    }

    public static String generarBitacora() {
        String archivo = nombre("reporte_bitacora");
        StringBuilder html = new StringBuilder(inicio("Reporte de bitácora"));
        html.append("<table><tr><th>Fecha</th><th>Usuario</th><th>Acción</th><th>Detalle</th></tr>");
        String[] lineas = BitacoraUtil.leer();
        for (int i = 0; i < lineas.length; i++) {
            String[] p = lineas[i].split(";", -1);
            html.append("<tr>");
            for (int j = 0; j < 4; j++) html.append("<td>").append(j < p.length ? esc(p[j]) : "").append("</td>");
            html.append("</tr>");
        }
        html.append("</table>").append(fin());
        if (escribir(archivo, html.toString())) {
            BitacoraUtil.registrar("Sistema", "REPORTE", "Generado reporte de bitácora");
            return new File(archivo).getAbsolutePath();
        }
        return null;
    }

    private static String esc(String texto) {
        if (texto == null) return "";
        return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
}
