package interfaz;

import reportes.ReporteHTML;
import util.BitacoraUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VentanaReportes extends JFrame {
    private final JButton btnAnimales = new JButton("Reporte de animales");
    private final JButton btnAdopciones = new JButton("Reporte de adopciones");
    private final JButton btnOcupacion = new JButton("Reporte de ocupación");
    private final JButton btnBitacora = new JButton("Reporte de bitácora");
    private final JButton btnRegresar = new JButton("Regresar");
    private final JTextArea txtResultado = new JTextArea();

    public VentanaReportes() {
        setTitle("Reportes HTML");
        setSize(650, 430);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        crearInterfaz();
    }

    private void crearInterfaz() {
        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("REPORTES DEL CENTRO DE RESCATE", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        principal.add(titulo, BorderLayout.NORTH);

        JPanel botones = new JPanel(new GridLayout(5, 1, 8, 8));
        botones.add(btnAnimales);
        botones.add(btnAdopciones);
        botones.add(btnOcupacion);
        botones.add(btnBitacora);
        botones.add(btnRegresar);
        principal.add(botones, BorderLayout.WEST);

        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        txtResultado.setText("Selecciona un reporte para generarlo en formato HTML.");
        principal.add(new JScrollPane(txtResultado), BorderLayout.CENTER);
        add(principal);

        btnAnimales.addActionListener(e -> generar("animales"));
        btnAdopciones.addActionListener(e -> generar("adopciones"));
        btnOcupacion.addActionListener(e -> generar("ocupacion"));
        btnBitacora.addActionListener(e -> generar("bitacora"));
        btnRegresar.addActionListener(e -> {
            new VentanaPrincipal().setVisible(true);
            dispose();
        });
    }

    private void generar(String tipo) {
        String ruta = null;
        if ("animales".equals(tipo)) ruta = ReporteHTML.generarAnimales();
        if ("adopciones".equals(tipo)) ruta = ReporteHTML.generarAdopciones();
        if ("ocupacion".equals(tipo)) ruta = ReporteHTML.generarOcupacion();
        if ("bitacora".equals(tipo)) ruta = ReporteHTML.generarBitacora();

        if (ruta == null) {
            txtResultado.setText("No se pudo generar el reporte.");
            return;
        }

        txtResultado.setText("Reporte generado correctamente:\n\n" + ruta);
        int opcion = JOptionPane.showConfirmDialog(this,
                "Reporte generado correctamente. ¿Deseas abrirlo en el navegador?",
                "Reporte generado", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            try {
                Desktop.getDesktop().open(new File(ruta));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "No se pudo abrir automáticamente. Puedes abrir el archivo desde la ruta mostrada.",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
