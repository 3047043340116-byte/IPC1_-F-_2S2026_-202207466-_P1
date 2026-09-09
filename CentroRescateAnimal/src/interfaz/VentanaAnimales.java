package interfaz;

import datos.AnimalDatos;
import modelo.Animal;
import persistencia.AnimalPersistencia;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class VentanaAnimales extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtEspecie;

    private JComboBox<String> cmbEstado;

    private JButton btnRegistrar;
    private JButton btnLimpiar;
    private JButton btnEliminar;
    private JButton btnActualizar;
    private JButton btnCerrar;

    private JTable tablaAnimales;
    private DefaultTableModel modeloTabla;

    private AnimalDatos datosAnimales;
    private AnimalPersistencia persistencia;

    public VentanaAnimales() {

        setTitle("Módulo de Animales");
        setSize(800, 600);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        datosAnimales = new AnimalDatos(100);

        persistencia = new AnimalPersistencia("animales.txt");

        cargarDatos();

        crearInterfaz();

        actualizarTabla();
    }

    private void crearInterfaz() {

        JPanel panelPrincipal =
                new JPanel(new BorderLayout(10, 10));

        JPanel panelFormulario =
                new JPanel(new GridLayout(5, 2, 5, 5));

        JLabel lblCodigo =
                new JLabel("Código:");

        JLabel lblNombre =
                new JLabel("Nombre:");

        JLabel lblEspecie =
                new JLabel("Especie:");

        JLabel lblEstado =
                new JLabel("Estado:");

        txtCodigo = new JTextField();

        txtNombre = new JTextField();

        txtEspecie = new JTextField();

        cmbEstado = new JComboBox<>();

        cmbEstado.addItem("Rescatado");
        cmbEstado.addItem("En atención");
        cmbEstado.addItem("Disponible");
        cmbEstado.addItem("Adoptado");

        panelFormulario.add(lblCodigo);
        panelFormulario.add(txtCodigo);

        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);

        panelFormulario.add(lblEspecie);
        panelFormulario.add(txtEspecie);

        panelFormulario.add(lblEstado);
        panelFormulario.add(cmbEstado);

        btnRegistrar =
                new JButton("Registrar");

        btnLimpiar =
                new JButton("Limpiar");

        panelFormulario.add(btnRegistrar);
        panelFormulario.add(btnLimpiar);

        panelPrincipal.add(
                panelFormulario,
                BorderLayout.NORTH
        );

        modeloTabla =
                new DefaultTableModel();

        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Especie");
        modeloTabla.addColumn("Estado");

        tablaAnimales =
                new JTable(modeloTabla);

        JScrollPane scroll =
                new JScrollPane(tablaAnimales);

        panelPrincipal.add(
                scroll,
                BorderLayout.CENTER
        );

        JPanel panelBotones =
                new JPanel();

        btnActualizar =
                new JButton("Actualizar estado");

        btnEliminar =
                new JButton("Eliminar");

        btnCerrar =
                new JButton("Cerrar");

        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(
                panelBotones,
                BorderLayout.SOUTH
        );

        add(panelPrincipal);

        configurarEventos();
    }

    private void configurarEventos() {

        btnRegistrar.addActionListener(
                e -> registrarAnimal()
        );

        btnLimpiar.addActionListener(
                e -> limpiarCampos()
        );

        btnActualizar.addActionListener(
                e -> actualizarEstado()
        );

        btnEliminar.addActionListener(
                e -> eliminarAnimal()
        );

        btnCerrar.addActionListener(
                e -> dispose()
        );
    }

    private void registrarAnimal() {

        String codigo =
                txtCodigo.getText().trim();

        String nombre =
                txtNombre.getText().trim();

        String especie =
                txtEspecie.getText().trim();

        String estado =
                cmbEstado.getSelectedItem().toString();

        if (codigo.isEmpty()
                || nombre.isEmpty()
                || especie.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Complete todos los campos."
            );

            return;
        }

        Animal animal =
                new Animal(
                        codigo,
                        nombre,
                        especie,
                        estado
                );

        boolean registrado =
                datosAnimales.registrarAnimal(animal);

        if (registrado) {

            persistencia.guardar(datosAnimales);

            actualizarTabla();

            limpiarCampos();

            JOptionPane.showMessageDialog(
                    this,
                    "Animal registrado correctamente."
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar el animal.\n"
                    + "Verifique que el código no esté repetido."
            );
        }
    }

    private void actualizarTabla() {

        modeloTabla.setRowCount(0);

        for (int i = 0;
                i < datosAnimales.getCantidad();
                i++) {

            Animal animal =
                    datosAnimales.getAnimal(i);

            if (animal != null
                    && !animal.getEstado()
                            .equals("Eliminado")) {

                modeloTabla.addRow(
                        new Object[]{
                            animal.getCodigo(),
                            animal.getNombre(),
                            animal.getEspecie(),
                            animal.getEstado()
                        }
                );
            }
        }
    }

    private void limpiarCampos() {

        txtCodigo.setText("");
        txtNombre.setText("");
        txtEspecie.setText("");

        cmbEstado.setSelectedIndex(0);

        txtCodigo.requestFocus();
    }

    private void eliminarAnimal() {

        int fila =
                tablaAnimales.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un animal de la tabla."
            );

            return;
        }

        String codigo =
                modeloTabla.getValueAt(
                        fila,
                        0
                ).toString();

        int confirmacion =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro de eliminar este animal?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        boolean eliminado =
                datosAnimales.eliminarAnimal(codigo);

        if (eliminado) {

            persistencia.guardar(datosAnimales);

            actualizarTabla();

            limpiarCampos();

            JOptionPane.showMessageDialog(
                    this,
                    "Animal eliminado correctamente."
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo eliminar el animal."
            );
        }
    }

    private void actualizarEstado() {

        int fila =
                tablaAnimales.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un animal."
            );

            return;
        }

        String codigo =
                modeloTabla.getValueAt(
                        fila,
                        0
                ).toString();

        String[] estados = {
            "Rescatado",
            "En atención",
            "Disponible",
            "Adoptado"
        };

        String nuevoEstado =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Seleccione el nuevo estado:",
                        "Actualizar estado",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        estados,
                        estados[0]
                );

        if (nuevoEstado == null) {
            return;
        }

        boolean actualizado =
                datosAnimales.editarEstado(
                        codigo,
                        nuevoEstado
                );

        if (actualizado) {

            persistencia.guardar(datosAnimales);

            actualizarTabla();

            JOptionPane.showMessageDialog(
                    this,
                    "Estado actualizado correctamente."
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo actualizar el estado."
            );
        }
    }

    private void cargarDatos() {

        persistencia.cargar(datosAnimales);
    }
}