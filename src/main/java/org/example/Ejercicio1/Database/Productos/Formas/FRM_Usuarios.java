package org.example.Ejercicio1.Database.Productos.Formas;

import org.example.Ejercicio1.Database.Connection.Conexion_BD;
import org.example.Ejercicio1.Database.Dao.UsuarioDAO;
import org.example.Ejercicio1.Database.Model.Usuario;
import org.example.Ejercicio1.Database.Service.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FRM_Usuarios extends JFrame {
    private javax.swing.JPanel JPanel;
    private JLabel lbl_IDU;
    private JLabel lbl_Carnet;
    private JLabel lbl_Nombre;
    private JLabel lbl_Correo;
    private JLabel lbl_Seccion;
    private JLabel lbl_TID;
    private JLabel lbl_Activo;
    private JTextField textField_IDU;
    private JTextField textField_Carnet;
    private JTextField textField_Nombre;
    private JTextField textField_Correo;
    private JTextField textField_Seccion;
    private JTextField textField_TID;
    private JTextField textField_Activo;
    private JButton createButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;

    public FRM_Usuarios() {
        // Establecer conexión a la base de datos
        Conexion_BD conexionBD = new Conexion_BD();
        Connection conn = conexionBD.getConnection();

        if (conn != null) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
            UsuarioService usuarioService = new UsuarioService(usuarioDAO);
        }

        // Acción del botón Agregar
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
            }
        });

        // Acción del botón Modificar
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarUsuario();
            }
        });

        // Acción del botón Eliminar
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });

        // Acción del botón Buscar
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });
    }

    // Método para agregar un usuario
    private void agregarUsuario() {
        try {
            String correo = textField_Correo.getText();

            // Verificar si el correo ya existe
            if (UsuarioService.existeCorreo(correo)) {
                JOptionPane.showMessageDialog(null, "El correo ya está en uso. Por favor, use uno diferente.");
                return;  // Si el correo ya existe, detenemos la ejecución del método
            }

            // Si el correo no existe, procedemos a agregar el usuario
            Usuario usuario = new Usuario();
            usuario.setCarne(textField_Carnet.getText());
            usuario.setNombre(textField_Nombre.getText());
            usuario.setCorreo(correo);
            usuario.setSeccion(textField_Seccion.getText());
            usuario.setTelegramId(Long.parseLong(textField_TID.getText()));
            usuario.setActivo(textField_Activo.getText());

            UsuarioService.agregarUsuario(usuario);
            JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente");
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el usuario");
            ex.printStackTrace();
        }
    }

    // Método para modificar un usuario
    private void modificarUsuario() {
        try {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(Integer.parseInt(textField_IDU.getText()));
            usuario.setCarne(textField_Carnet.getText());
            usuario.setNombre(textField_Nombre.getText());
            usuario.setCorreo(textField_Correo.getText());
            usuario.setSeccion(textField_Seccion.getText());
            usuario.setTelegramId(Long.parseLong(textField_TID.getText()));
            usuario.setActivo(textField_Activo.getText());

            UsuarioService.modificarUsuario(usuario);
            JOptionPane.showMessageDialog(null, "Usuario modificado exitosamente");
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar el usuario");
            ex.printStackTrace();
        }
    }

    // Método para eliminar un usuario
    private void eliminarUsuario() {
        try {
            int idUsuario = Integer.parseInt(textField_IDU.getText());
            UsuarioService.eliminarUsuario(idUsuario);
            JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario");
            ex.printStackTrace();
        }
    }

    // Método para buscar un usuario por ID
    private void buscarUsuario() {
        try {
            int idUsuario = Integer.parseInt(textField_IDU.getText());
            Usuario usuario = UsuarioService.buscarUsuarioPorId(idUsuario);

            if (usuario != null) {
                textField_Carnet.setText(usuario.getCarne());
                textField_Nombre.setText(usuario.getNombre());
                textField_Correo.setText(usuario.getCorreo());
                textField_Seccion.setText(usuario.getSeccion());
                textField_TID.setText(String.valueOf(usuario.getTelegramId()));
                textField_Activo.setText(usuario.getActivo());
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                limpiarCampos();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar el usuario");
            ex.printStackTrace();
        }
    }

    // Método para limpiar los campos de texto
    private void limpiarCampos() {
        textField_IDU.setText("");
        textField_Carnet.setText("");
        textField_Nombre.setText("");
        textField_Correo.setText("");
        textField_Seccion.setText("");
        textField_TID.setText("");
        textField_Activo.setText("");
    }

}