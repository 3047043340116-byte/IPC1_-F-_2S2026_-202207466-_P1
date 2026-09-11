package interfaz;

import util.BitacoraUtil;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class VentanaDatosEstudiante extends JFrame {
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtCarnet = new JTextField();
    private final JTextField txtSeccion = new JTextField();
    private final JButton btnGuardar = new JButton("Guardar");
    private final JButton btnLimpiar = new JButton("Limpiar");
    private final JButton btnRegresar = new JButton("Regresar");

    public VentanaDatosEstudiante() {
        setTitle("Datos del estudiante");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        crearInterfaz();
        cargarDatos();
    }

    private void crearInterfaz() {
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel titulo = new JLabel("DATOS DEL ESTUDIANTE", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        p.add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(3, 2, 8, 8));
        formulario.add(new JLabel("Nombre:")); formulario.add(txtNombre);
        formulario.add(new JLabel("Carné:")); formulario.add(txtCarnet);
        formulario.add(new JLabel("Sección:")); formulario.add(txtSeccion);
        p.add(formulario, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botones.add(btnGuardar); botones.add(btnLimpiar); botones.add(btnRegresar);
        p.add(botones, BorderLayout.SOUTH);
        add(p);

        btnGuardar.addActionListener(e -> guardarDatos());
        btnLimpiar.addActionListener(e -> limpiar());
        btnRegresar.addActionListener(e -> { new VentanaPrincipal().setVisible(true); dispose(); });
    }

    private void cargarDatos() {
        File f = new File("estudiante.txt");
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";", -1);
                if (p.length >= 3) {
                    txtNombre.setText(p[0]);
                    txtCarnet.setText(p[1]);
                    txtSeccion.setText(p[2]);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los datos.");
        }
    }

    private void guardarDatos() {
        String nombre = txtNombre.getText().trim();
        String carnet = txtCarnet.getText().trim();
        String seccion = txtSeccion.getText().trim();
        if (nombre.isEmpty() || carnet.isEmpty() || seccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter("estudiante.txt"))) {
            pw.println(nombre.replace(";", ",") + ";" + carnet.replace(";", ",") + ";" + seccion.replace(";", ","));
            BitacoraUtil.registrar("Sistema", "DATOS", "Actualización de datos del estudiante");
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudieron guardar los datos.");
        }
    }

    private void limpiar() {
        txtNombre.setText("");
        txtCarnet.setText("");
        txtSeccion.setText("");
    }
}
