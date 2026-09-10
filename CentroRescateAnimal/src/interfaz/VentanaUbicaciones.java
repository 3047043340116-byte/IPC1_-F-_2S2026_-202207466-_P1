/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import datos.EspacioRefugioDatos;
import datos.AnimalDatos;

import modelo.EspacioRefugio;
import modelo.Animal;

import persistencia.EspacioRefugioPersistencia;
import persistencia.AnimalPersistencia;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Osvin
 */
public class VentanaUbicaciones extends javax.swing.JFrame {

    private EspacioRefugioDatos datosEspacios;
    private EspacioRefugioPersistencia persistencia;

    private AnimalDatos datosAnimales;
    private DefaultTableModel modeloTabla;

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(VentanaUbicaciones.class.getName());

    public VentanaUbicaciones() {

        initComponents();

        setDefaultCloseOperation(
                javax.swing.WindowConstants.DISPOSE_ON_CLOSE
        );

        datosEspacios = new EspacioRefugioDatos(4, 5);

        persistencia =
                new EspacioRefugioPersistencia("espacios.txt");

        datosAnimales = new AnimalDatos(100);

        AnimalPersistencia persistenciaAnimales =
                new AnimalPersistencia("animales.txt");

        persistenciaAnimales.cargar(datosAnimales);

        configurarTabla();
        cargarDatos();
        actualizarTabla();
        configurarEventos();

        setLocationRelativeTo(null);
    }

    private void configurarTabla() {

        modeloTabla = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Área",
                    "Espacio",
                    "Estado",
                    "Código Animal"
                }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jTable1.setModel(modeloTabla);
    }

    private void cargarDatos() {
        persistencia.cargar(datosEspacios);
    }

    private void configurarEventos() {

        btnConsultar.addActionListener(e -> consultarEspacio());

        btnAsignar.addActionListener(e -> asignarAnimal());

        btnLiberar.addActionListener(e -> liberarEspacio());

        btnRegresar.addActionListener(e -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
            this.dispose();
        });

        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarEspacioSeleccionado();
            }
        });
    }

    private int obtenerFila() {

        if (cmbArea.getSelectedItem() == null) {
            return -1;
        }

        String area = cmbArea.getSelectedItem().toString();

        switch (area) {
            case "Recepción":
                return 0;
            case "Cuarentena":
                return 1;
            case "Recuperación":
                return 2;
            case "Adopción":
                return 3;
            default:
                return -1;
        }
    }

    private int obtenerColumna() {

        if (cmbEspacio.getSelectedItem() == null) {
            return -1;
        }

        String espacio = cmbEspacio.getSelectedItem().toString();

        try {
            return Integer.parseInt(espacio) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void consultarEspacio() {

        int fila = obtenerFila();
        int columna = obtenerColumna();

        if (fila == -1 || columna == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un área y un espacio válidos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        EspacioRefugio espacio =
                datosEspacios.consultarEspacio(fila, columna);

        if (espacio == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo consultar el espacio.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (espacio.estaDisponible()) {
            JOptionPane.showMessageDialog(
                    this,
                    "El espacio está disponible.",
                    "Consulta",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "El espacio está ocupado por el animal: "
                    + espacio.getCodigoAnimal(),
                    "Consulta",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void asignarAnimal() {

        int fila = obtenerFila();
        int columna = obtenerColumna();

        String codigoAnimal = txtCodigoAnimal.getText().trim();

        if (fila == -1 || columna == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un área y un espacio válidos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (codigoAnimal.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ingresa el código del animal.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            txtCodigoAnimal.requestFocus();
            return;
        }

        Animal animal = datosAnimales.buscarAnimal(codigoAnimal);

        if (animal == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No existe un animal con ese código.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if ("Eliminado".equals(animal.getEstado())) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se puede asignar un animal eliminado.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if ("Adoptado".equals(animal.getEstado())) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se puede asignar un animal adoptado.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        EspacioRefugio espacio =
                datosEspacios.consultarEspacio(fila, columna);

        if (espacio == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "El espacio no existe.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (!espacio.estaDisponible()) {
            JOptionPane.showMessageDialog(
                    this,
                    "El espacio ya está ocupado por el animal: "
                    + espacio.getCodigoAnimal(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (datosEspacios.buscarAnimal(codigoAnimal) != null) {
            JOptionPane.showMessageDialog(
                    this,
                    "El animal ya está asignado a otro espacio.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        boolean asignado =
                datosEspacios.asignarAnimal(
                        fila,
                        columna,
                        codigoAnimal
                );

        if (asignado) {

            persistencia.guardar(datosEspacios);
            actualizarTabla();

            JOptionPane.showMessageDialog(
                    this,
                    "Animal asignado correctamente.",
                    "Asignación",
                    JOptionPane.INFORMATION_MESSAGE
            );

            txtCodigoAnimal.setText("");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo asignar el animal.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void liberarEspacio() {

        int fila = obtenerFila();
        int columna = obtenerColumna();

        if (fila == -1 || columna == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un área y un espacio válidos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        EspacioRefugio espacio =
                datosEspacios.consultarEspacio(fila, columna);

        if (espacio == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "El espacio no existe.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (espacio.estaDisponible()) {
            JOptionPane.showMessageDialog(
                    this,
                    "El espacio ya está disponible.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas liberar el espacio ocupado por el animal "
                + espacio.getCodigoAnimal() + "?",
                "Confirmar liberación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        boolean liberado =
                datosEspacios.liberarEspacio(fila, columna);

        if (liberado) {

            persistencia.guardar(datosEspacios);
            actualizarTabla();
            txtCodigoAnimal.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    "Espacio liberado correctamente.",
                    "Liberación",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo liberar el espacio.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void actualizarTabla() {

        if (modeloTabla == null) {
            return;
        }

        modeloTabla.setRowCount(0);

        for (int i = 0; i < datosEspacios.getFilas(); i++) {

            for (int j = 0; j < datosEspacios.getColumnas(); j++) {

                EspacioRefugio espacio =
                        datosEspacios.consultarEspacio(i, j);

                String area;

                switch (i) {
                    case 0:
                        area = "Recepción";
                        break;
                    case 1:
                        area = "Cuarentena";
                        break;
                    case 2:
                        area = "Recuperación";
                        break;
                    case 3:
                        area = "Adopción";
                        break;
                    default:
                        area = "Desconocida";
                        break;
                }

                String numeroEspacio = String.valueOf(j + 1);

                String estado;
                String codigoAnimal;

                if (espacio == null) {

                    estado = "No disponible";
                    codigoAnimal = "";

                } else if (espacio.estaDisponible()) {

                    estado = "Disponible";
                    codigoAnimal = "";

                } else {

                    estado = "Ocupado";
                    codigoAnimal = espacio.getCodigoAnimal();
                }

                modeloTabla.addRow(
                        new Object[]{
                            area,
                            numeroEspacio,
                            estado,
                            codigoAnimal
                        }
                );
            }
        }
    }

    private void cargarEspacioSeleccionado() {

        int fila = jTable1.getSelectedRow();

        if (fila == -1) {
            return;
        }

        Object valorArea = jTable1.getValueAt(fila, 0);
        Object valorEspacio = jTable1.getValueAt(fila, 1);
        Object valorCodigo = jTable1.getValueAt(fila, 3);

        if (valorArea != null) {
            cmbArea.setSelectedItem(valorArea.toString());
        }

        if (valorEspacio != null) {
            cmbEspacio.setSelectedItem(valorEspacio.toString());
        }

        if (valorCodigo != null) {
            txtCodigoAnimal.setText(valorCodigo.toString());
        } else {
            txtCodigoAnimal.setText("");
        }
    }

    private void limpiarCampos() {

        txtCodigoAnimal.setText("");

        cmbArea.setSelectedIndex(0);

        cmbEspacio.setSelectedIndex(0);

        jTable1.clearSelection();

        txtCodigoAnimal.requestFocus();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbArea = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbEspacio = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtCodigoAnimal = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        btnAsignar = new javax.swing.JButton();
        btnLiberar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de ubicaciones");

        jLabel1.setText("Gestión de ubicaciones");

        jLabel2.setText("Área:");

        cmbArea.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{
                    "Recepción",
                    "Cuarentena",
                    "Recuperación",
                    "Adopción"
                }
        ));

        jLabel3.setText("Espacio:");

        cmbEspacio.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{
                    "1", "2", "3", "4", "5"
                }
        ));

        jLabel4.setText("Código animal:");

        btnConsultar.setText("Consultar");
        btnAsignar.setText("Asignar");
        btnLiberar.setText("Liberar");
        btnRegresar.setText("Regresar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Área", "Espacio", "Estado", "Código Animal"
                }
        ));

        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout =
                new javax.swing.GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING
                )
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING
                        )
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING
                        )
                        .addComponent(
                                cmbEspacio,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(
                                cmbArea,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                89,
                                javax.swing.GroupLayout.PREFERRED_SIZE
                        )))
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED
                                )
                                .addComponent(
                                        txtCodigoAnimal,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        89,
                                        javax.swing.GroupLayout.PREFERRED_SIZE
                                ))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btnConsultar)
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED
                                )
                                .addComponent(btnAsignar)
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED
                                )
                                .addComponent(btnLiberar)))
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.RELATED
                        )
                        .addComponent(btnRegresar))
                .addComponent(
                        jScrollPane1,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        444,
                        javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel1))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING
                )
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.BASELINE
                        )
                        .addComponent(jLabel2)
                        .addComponent(
                                cmbArea,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE
                        ))
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
                        )
                        .addGroup(layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.BASELINE
                        )
                        .addComponent(jLabel3)
                        .addComponent(
                                cmbEspacio,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE
                        ))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.BASELINE
                        )
                        .addComponent(jLabel4)
                        .addComponent(
                                txtCodigoAnimal,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE
                        ))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.BASELINE
                        )
                        .addComponent(btnConsultar)
                        .addComponent(btnAsignar)
                        .addComponent(
                                btnLiberar,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                23,
                                javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(btnRegresar))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(
                                jScrollPane1,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                292,
                                javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addContainerGap())
        );

        pack();
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info :
                    javax.swing.UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(
                            info.getClassName()
                    );
                    break;
                }
            }

        } catch (
                ReflectiveOperationException |
                javax.swing.UnsupportedLookAndFeelException ex
                ) {

            logger.log(
                    java.util.logging.Level.SEVERE,
                    null,
                    ex
            );
        }

        java.awt.EventQueue.invokeLater(
                () -> new VentanaUbicaciones().setVisible(true)
        );
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnLiberar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbArea;
    private javax.swing.JComboBox<String> cmbEspacio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCodigoAnimal;
    // End of variables declaration
}
