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
    private JButton btnSalir;

    public VentanaPrincipal() {
        setTitle("Centro de Rescate Animal");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        crearInterfaz();
    }

    private void crearInterfaz() {

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        JLabel titulo = new JLabel(
                "CENTRO DE RESCATE ANIMAL",
                SwingConstants.CENTER
        );
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        panelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(
                new GridLayout(4, 2, 10, 10)
        );

        btnAnimales = new JButton("Animales");
        btnAdoptantes = new JButton("Adoptantes");
        btnSolicitudes = new JButton("Solicitudes");
        btnRescates = new JButton("Rescates");
        btnUbicaciones = new JButton("Ubicaciones");
        btnReportes = new JButton("Reportes");
        btnSalir = new JButton("Salir");

        panelBotones.add(btnAnimales);
        panelBotones.add(btnAdoptantes);
        panelBotones.add(btnSolicitudes);
        panelBotones.add(btnRescates);
        panelBotones.add(btnUbicaciones);
        panelBotones.add(btnReportes);
        panelBotones.add(btnSalir);

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        add(panelPrincipal);

        configurarEventos();
    }

    private void configurarEventos() {

        btnAnimales.addActionListener(e -> {
            VentanaAnimales ventana = new VentanaAnimales();
            ventana.setVisible(true);
            this.setVisible(false);
        });

        btnAdoptantes.addActionListener(e -> {
    VentanaAdoptantes ventana = new VentanaAdoptantes();
    ventana.setVisible(true);
    this.setVisible(false);
});
        

        btnSolicitudes.addActionListener(e -> {
    VentanaSolicitudes ventana = new VentanaSolicitudes();
    ventana.setVisible(true);
    this.setVisible(false);
});

        btnRescates.addActionListener(e -> mostrarPendiente("Rescates"));

        btnUbicaciones.addActionListener(e -> mostrarPendiente("Ubicaciones"));

        btnReportes.addActionListener(e -> mostrarPendiente("Reportes"));

        btnSalir.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea salir del sistema?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private void mostrarPendiente(String modulo) {
        JOptionPane.showMessageDialog(
                this,
                "Este módulo se implementará en la siguiente etapa de la interfaz gráfica.",
                modulo,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
