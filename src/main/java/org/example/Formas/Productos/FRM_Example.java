package org.example.Formas.Productos;

import org.example.Ejercicio1.Database.Model.Datos;
import org.example.Ejercicio1.Database.Service.DatosService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.lang.String;
import java.util.Date;

public class FRM_Example {
    public static void main(String[] args) {
        JFrame frame = new JFrame("FRM_Example");
        frame.setContentPane(new FRM_Example().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel rootPanel;
    private JLabel lbl_Nombre;
    private JLabel lbl_Apellido;
    private JTextField textField_Apellido;
    private JLabel lbl_Departamento;
    private JTextField textField_Departamento;
    private JLabel lbl_FDN;

    private javax.swing.JPanel JPanel;
    private JTextField textField_Nombre;
    private JTextField textField_FDN;
    private JButton button_Create;
    private JButton button_Reload;
    private JButton button_Update;
    private JButton button_Delete;
    private JLabel lbl_Codigo;
    private JTextField textField_Codigo;

    private DatosService datosService;

    public FRM_Example() {
        // Inicializa el servicio con la conexión a la base de datos
        this.datosService = new DatosService();

        // Lógica del botón "Agregar"
        button_Create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarDatos();
            }
        });

        // Lógica del botón "Modificar"
        button_Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarDatos();
            }
        });

        // Lógica del botón "Eliminar"
        button_Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDatos();
            }
        });

        // Lógica del botón "Buscar"
        button_Reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarDatos();
            }
        });
    }

    // Método para agregar datos
    private void agregarDatos() {
        try {
            // Usar un formato de fecha como "dd/MM/yyyy"
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaNacimiento = sdf.parse(textField_FDN.getText());
            Datos datos = new Datos();
            datos.setFechaNacimiento(fechaNacimiento);
            // Guardar en la base de datos usando el servicio
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        // Método para modificar datos
        private void modificarDatos () {
            try {
                Datos datos = new Datos();
                // Suponiendo que tienes un campo para el código o seleccionas de algún lugar
                datos.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código para modificar:")));
                datos.setNombre(textField_Nombre.getText());
                datos.setApellido(textField_Apellido.getText());
                datos.setDepartamento(textField_Departamento.getText());

                Date fechaNacimiento = new SimpleDateFormat("dd/MM/yyyy").parse(textField_FDN.getText());
                datos.setFechaNacimiento(fechaNacimiento);

                // Actualizar los datos en la base de datos
                datosService.modificarDatos(datos);

                JOptionPane.showMessageDialog(null, "Datos modificados exitosamente.");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al modificar datos.");
            }
        }

        // Método para eliminar datos
        private void eliminarDatos () {
            try {
                int codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código para eliminar:"));
                datosService.eliminarDatos(codigo);

                JOptionPane.showMessageDialog(null, "Datos eliminados exitosamente.");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al eliminar datos.");
            }
        }

        // Método para buscar datos
        private void buscarDatos () {
            try {
                int codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código para buscar:"));
                Datos datos = datosService.buscarDatosPorCodigo(codigo);

                if (datos != null) {
                    textField_Nombre.setText(datos.getNombre());
                    textField_Apellido.setText(datos.getApellido());
                    textField_Departamento.setText(datos.getDepartamento());
                    textField_FDN.setText(new SimpleDateFormat("dd/MM/yyyy").format(datos.getFechaNacimiento()));
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontraron datos con ese código.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al buscar datos.");
            }
        }
    }