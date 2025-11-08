/**
 * Autora: Ana Paula Rosales Olguin
 * Fecha: 07 de noviembre de 2025
 */

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import classes.Usuario;
import classes.UsuarioManager;

public class InterfazGrafica extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private List<Usuario> usuarios;

    public InterfazGrafica() {
        super("Gestión de Usuarios");

        usuarios = UsuarioManager.cargarUsuarios();

        modelo = new DefaultTableModel(new Object[]{"Nombre", "Correo"}, 0);
        tabla = new JTable(modelo);
        cargarTabla();

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");   // <-- agregado

        btnAgregar.addActionListener(e -> agregarUsuario());
        btnEditar.addActionListener(e -> editarUsuario());
        btnEliminar.addActionListener(e -> eliminarUsuario());   // <-- agregado

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);   // <-- agregado

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(tabla), BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);

        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        for (Usuario u : usuarios) {
            modelo.addRow(new Object[]{u.getNombre(), u.getCorreo()});
        }
    }

    private void agregarUsuario() {
        JTextField campoNombre = new JTextField();
        JTextField campoCorreo = new JTextField();
        Object[] campos = {
            "Nombre:", campoNombre,
            "Correo:", campoCorreo
        };

        int opcion = JOptionPane.showConfirmDialog(this, campos, "Agregar Usuario", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            Usuario nuevo = new Usuario(campoNombre.getText(), campoCorreo.getText());
            usuarios.add(nuevo);
            UsuarioManager.guardarUsuarios(usuarios);
            cargarTabla();
        }
    }

    private void editarUsuario() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para editar.");
            return;
        }

        Usuario u = usuarios.get(filaSeleccionada);

        JTextField campoNombre = new JTextField(u.getNombre());
        JTextField campoCorreo = new JTextField(u.getCorreo());
        Object[] campos = {
            "Nombre:", campoNombre,
            "Correo:", campoCorreo
        };

        int opcion = JOptionPane.showConfirmDialog(this, campos, "Editar Usuario", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            u.setNombre(campoNombre.getText());
            u.setCorreo(campoCorreo.getText());
            UsuarioManager.guardarUsuarios(usuarios);
            cargarTabla();
        }
    }

    // ---------- NUEVA FUNCIÓN ELIMINAR USUARIO ----------
    private void eliminarUsuario() {
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que quieres eliminar este usuario?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            usuarios.remove(filaSeleccionada);
            UsuarioManager.guardarUsuarios(usuarios);
            cargarTabla();
        }
    }
    // ------------------------------------------------------

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazGrafica());
    }
}
