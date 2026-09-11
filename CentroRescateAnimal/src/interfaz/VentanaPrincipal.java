package interfaz;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JButton btnAnimales;
    private JButton btnAdoptantes;
    private JButton btnSolicitudes;
    private JButton btnRescates;
    private JButton btnUbicaciones;
    private JButton btnReportes;
    private JButton btnDatos;
    private JButton btnSalir;

    public VentanaPrincipal() {
        setTitle("Centro de Rescate Animal");
        setSize(720, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        crearInterfaz();
    }

    private void crearInterfaz() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("CENTRO DE RESCATE ANIMAL", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(4, 2, 10, 10));
        btnAnimales = new JButton("Animales");
        btnAdoptantes = new JButton("Adoptantes");
        btnSolicitudes = new JButton("Solicitudes");
        btnRescates = new JButton("Rescates");
        btnUbicaciones = new JButton("Ubicaciones");
        btnReportes = new JButton("Reportes");
        btnDatos = new JButton("Datos del estudiante");
        btnSalir = new JButton("Salir");

        panelBotones.add(btnAnimales);
        panelBotones.add(btnAdoptantes);
        panelBotones.add(btnSolicitudes);
        panelBotones.add(btnRescates);
        panelBotones.add(btnUbicaciones);
        panelBotones.add(btnReportes);
        panelBotones.add(btnDatos);
        panelBotones.add(btnSalir);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        JLabel pie = new JLabel("Gestión de animales, adopciones, rescates y espacios", SwingConstants.CENTER);
        panelPrincipal.add(pie, BorderLayout.SOUTH);
        add(panelPrincipal);
        configurarEventos();
    }

    private void configurarEventos() {
        btnAnimales.addActionListener(e -> abrir(new VentanaAnimales()));
        btnAdoptantes.addActionListener(e -> abrir(new VentanaAdoptantes()));
        btnSolicitudes.addActionListener(e -> abrir(new VentanaSolicitudes()));
        btnRescates.addActionListener(e -> abrir(new VentanaRescates()));
        btnUbicaciones.addActionListener(e -> abrir(new VentanaUbicaciones()));
        btnReportes.addActionListener(e -> abrir(new VentanaReportes()));
        btnDatos.addActionListener(e -> abrir(new VentanaDatosEstudiante()));
        btnSalir.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Desea salir del sistema?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) System.exit(0);
        });
    }

    private void abrir(JFrame ventana) {
        ventana.setVisible(true);
        setVisible(false);
    }
}
