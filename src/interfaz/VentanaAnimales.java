package interfaz;

import datos.AnimalDatos;
import modelo.Animal;
import persistencia.AnimalPersistencia;
import util.BitacoraUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaAnimales extends JFrame {
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtEspecie;
    private JTextField txtBuscar;
    private JComboBox<String> cmbEstado;
    private JComboBox<String> cmbCriterio;
    private JButton btnRegistrar;
    private JButton btnBuscar;
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
        setSize(900, 620);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        datosAnimales = new AnimalDatos(100);
        persistencia = new AnimalPersistencia("animales.txt");
        cargarDatos();
        crearInterfaz();
        actualizarTabla();
    }

    private void crearInterfaz() {
        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("GESTIÓN DE ANIMALES", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        principal.add(titulo, BorderLayout.NORTH);

        JPanel superior = new JPanel(new BorderLayout(10, 10));
        JPanel formulario = new JPanel(new GridLayout(4, 2, 6, 6));
        txtCodigo = new JTextField();
        txtNombre = new JTextField();
        txtEspecie = new JTextField();
        cmbEstado = new JComboBox<>(new String[]{"Rescatado", "En atención", "Disponible", "Adoptado"});
        formulario.add(new JLabel("Código:")); formulario.add(txtCodigo);
        formulario.add(new JLabel("Nombre:")); formulario.add(txtNombre);
        formulario.add(new JLabel("Especie:")); formulario.add(txtEspecie);
        formulario.add(new JLabel("Estado:")); formulario.add(cmbEstado);
        superior.add(formulario, BorderLayout.CENTER);

        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        busqueda.setBorder(BorderFactory.createTitledBorder("Buscar por código, nombre, especie o estado"));
        cmbCriterio = new JComboBox<>(new String[]{"Código", "Nombre", "Especie", "Estado"});
        txtBuscar = new JTextField(14);
        btnBuscar = new JButton("Buscar");
        busqueda.add(cmbCriterio); busqueda.add(txtBuscar); busqueda.add(btnBuscar);
        superior.add(busqueda, BorderLayout.SOUTH);
        principal.add(superior, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new Object[][]{}, new String[]{"Código", "Nombre", "Especie", "Estado"}) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaAnimales = new JTable(modeloTabla);
        tablaAnimales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        principal.add(new JScrollPane(tablaAnimales), BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout());
        btnRegistrar = new JButton("Registrar");
        btnActualizar = new JButton("Actualizar estado");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnCerrar = new JButton("Regresar");
        botones.add(btnRegistrar); botones.add(btnActualizar); botones.add(btnEliminar);
        botones.add(btnLimpiar); botones.add(btnCerrar);
        principal.add(botones, BorderLayout.SOUTH);
        add(principal);

        btnRegistrar.addActionListener(e -> registrarAnimal());
        btnBuscar.addActionListener(e -> buscarAnimales());
        btnActualizar.addActionListener(e -> actualizarEstado());
        btnEliminar.addActionListener(e -> eliminarAnimal());
        btnLimpiar.addActionListener(e -> { limpiarCampos(); actualizarTabla(); });
        btnCerrar.addActionListener(e -> { new VentanaPrincipal().setVisible(true); dispose(); });
        tablaAnimales.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarAnimalSeleccionado();
        });
    }

    private void registrarAnimal() {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String especie = txtEspecie.getText().trim();
        String estado = cmbEstado.getSelectedItem().toString();
        if (codigo.isEmpty() || nombre.isEmpty() || especie.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (datosAnimales.buscarAnimal(codigo) != null) {
            JOptionPane.showMessageDialog(this, "El código ya está registrado.", "Duplicado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (datosAnimales.registrarAnimal(new Animal(codigo, nombre, especie, estado))) {
            persistencia.guardar(datosAnimales);
            BitacoraUtil.registrar("Sistema", "ANIMAL", "Registrado " + codigo);
            actualizarTabla(); limpiarCampos();
            JOptionPane.showMessageDialog(this, "Animal registrado correctamente.");
        } else JOptionPane.showMessageDialog(this, "No se pudo registrar el animal.");
    }

    private void buscarAnimales() {
        String criterio = txtBuscar.getText().trim();
        if (criterio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un criterio de búsqueda.");
            return;
        }
        int tipo = cmbCriterio.getSelectedIndex();
        modeloTabla.setRowCount(0);
        int encontrados = 0;
        for (int i = 0; i < datosAnimales.getCantidad(); i++) {
            Animal a = datosAnimales.getAnimal(i);
            if (a == null || "Eliminado".equals(a.getEstado())) continue;
            String valor = tipo == 0 ? a.getCodigo() : tipo == 1 ? a.getNombre() : tipo == 2 ? a.getEspecie() : a.getEstado();
            if (datosAnimales.coincideTexto(valor, criterio)) {
                modeloTabla.addRow(new Object[]{a.getCodigo(), a.getNombre(), a.getEspecie(), a.getEstado()});
                encontrados++;
            }
        }
        if (encontrados == 0) JOptionPane.showMessageDialog(this, "No se encontraron animales.");
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (int i = 0; i < datosAnimales.getCantidad(); i++) {
            Animal a = datosAnimales.getAnimal(i);
            if (a != null && !"Eliminado".equals(a.getEstado()))
                modeloTabla.addRow(new Object[]{a.getCodigo(), a.getNombre(), a.getEspecie(), a.getEstado()});
        }
    }

    private void cargarAnimalSeleccionado() {
        int fila = tablaAnimales.getSelectedRow();
        if (fila < 0) return;
        String codigo = tablaAnimales.getValueAt(fila, 0).toString();
        Animal a = datosAnimales.buscarAnimal(codigo);
        if (a == null) return;
        txtCodigo.setText(a.getCodigo()); txtNombre.setText(a.getNombre()); txtEspecie.setText(a.getEspecie()); cmbEstado.setSelectedItem(a.getEstado());
    }

    private void eliminarAnimal() {
        String codigo = txtCodigo.getText().trim();
        if (codigo.isEmpty() && tablaAnimales.getSelectedRow() >= 0) codigo = tablaAnimales.getValueAt(tablaAnimales.getSelectedRow(), 0).toString();
        if (codigo.isEmpty()) { JOptionPane.showMessageDialog(this, "Seleccione o ingrese un animal."); return; }
        int r = JOptionPane.showConfirmDialog(this, "¿Desea eliminar lógicamente el animal " + codigo + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (r != JOptionPane.YES_OPTION) return;
        if (datosAnimales.eliminarAnimal(codigo)) {
            persistencia.guardar(datosAnimales); BitacoraUtil.registrar("Sistema", "ANIMAL", "Eliminado lógicamente " + codigo);
            actualizarTabla(); limpiarCampos(); JOptionPane.showMessageDialog(this, "Animal eliminado correctamente.");
        } else JOptionPane.showMessageDialog(this, "No se pudo eliminar el animal.");
    }

    private void actualizarEstado() {
        String codigo = txtCodigo.getText().trim();
        if (codigo.isEmpty()) { JOptionPane.showMessageDialog(this, "Ingrese el código del animal."); return; }
        Animal a = datosAnimales.buscarAnimal(codigo);
        if (a == null || "Eliminado".equals(a.getEstado())) { JOptionPane.showMessageDialog(this, "No se encontró el animal."); return; }
        String[] estados = {"Rescatado", "En atención", "Disponible", "Adoptado"};
        String nuevo = (String) JOptionPane.showInputDialog(this, "Seleccione el nuevo estado:", "Actualizar estado", JOptionPane.QUESTION_MESSAGE, null, estados, a.getEstado());
        if (nuevo == null) return;
        if (datosAnimales.editarEstado(codigo, nuevo)) {
            persistencia.guardar(datosAnimales); BitacoraUtil.registrar("Sistema", "ANIMAL", "Estado de " + codigo + " cambiado a " + nuevo);
            actualizarTabla(); cmbEstado.setSelectedItem(nuevo); JOptionPane.showMessageDialog(this, "Estado actualizado correctamente.");
        } else JOptionPane.showMessageDialog(this, "No se pudo actualizar el estado.");
    }

    private void limpiarCampos() { txtCodigo.setText(""); txtNombre.setText(""); txtEspecie.setText(""); cmbEstado.setSelectedIndex(0); txtCodigo.requestFocus(); }
    private void cargarDatos() { persistencia.cargar(datosAnimales); }
}
