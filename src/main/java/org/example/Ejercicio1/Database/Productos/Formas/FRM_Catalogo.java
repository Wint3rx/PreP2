package org.example.Ejercicio1.Database.Productos.Formas;

import org.example.Ejercicio1.Database.Connection.Conexion_BD;
import org.example.Ejercicio1.Database.Dao.EquipoDAO;
import org.example.Ejercicio1.Database.Model.Equipo;
import org.example.Ejercicio1.Database.Service.EquipoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class FRM_Catalogo extends JFrame {
    private javax.swing.JPanel JPanel;
    private JLabel lbl_IDE;
    private JLabel lbl_Nombre;
    private JLabel lbl_Pais;
    private JLabel lbl_Cuidad;
    private JLabel lbl_Estadio;
    private JLabel lbl_Fundacion;
    private JLabel lbl_Entrenador;
    private JLabel lbl_WO;
    private JLabel lbl_FB;
    private JLabel lbl_X;
    private JLabel lbl_IG;
    private JLabel lbl_Patrocinador;
    private JLabel lbl_CE;
    private JButton createButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField textField_IDE;
    private JTextField textField_Nombre;
    private JTextField textField_Pais;
    private JTextField textField_Ciudad;
    private JTextField textField_Estadio;
    private JTextField textField_Fundacion;
    private JTextField textField_Entrenador;
    private JTextField textField_WO;
    private JTextField textField_FB;
    private JTextField textField_X;
    private JTextField textField_IG;
    private JTextField textField_Patrocinador;
    private JTextField textField_CE;

    private EquipoService equipoService;

    public FRM_Catalogo() {
        // Inicializar conexión y servicio
        Conexion_BD conexion_bd = new Conexion_BD();
        Connection connection = conexion_bd.getConnection();
        EquipoDAO equipoDAO = new EquipoDAO(connection);
        equipoService = new EquipoService(equipoDAO);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Equipo equipo = new Equipo();
                    equipo.setNombre(textField_Nombre.getText());
                    equipo.setPais(textField_Pais.getText());
                    equipo.setCiudad(textField_Ciudad.getText());
                    equipo.setEstadio(textField_Estadio.getText());
                    equipo.setFundacion(Integer.parseInt(textField_Fundacion.getText()));
                    equipo.setEntrenador(textField_Entrenador.getText());
                    equipo.setWebOficial(textField_WO.getText());
                    equipo.setFacebook(textField_FB.getText());
                    equipo.setTwitter(textField_X.getText());
                    equipo.setInstagram(textField_IG.getText());
                    equipo.setPatrocinadorPrincipal(textField_Patrocinador.getText());

                    equipoService.agregarEquipo(equipo);
                    JOptionPane.showMessageDialog(null, "Equipo agregado exitosamente");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar el equipo");
                    ex.printStackTrace();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Equipo equipo = new Equipo();
                    equipo.setIdEquipo(Integer.parseInt(textField_IDE.getText()));
                    equipo.setNombre(textField_Nombre.getText());
                    equipo.setPais(textField_Pais.getText());
                    equipo.setCiudad(textField_Ciudad.getText());
                    equipo.setEstadio(textField_Estadio.getText());
                    equipo.setFundacion(Integer.parseInt(textField_Fundacion.getText()));
                    equipo.setEntrenador(textField_Entrenador.getText());
                    equipo.setWebOficial(textField_WO.getText());
                    equipo.setFacebook(textField_FB.getText());
                    equipo.setTwitter(textField_X.getText());
                    equipo.setInstagram(textField_IG.getText());
                    equipo.setPatrocinadorPrincipal(textField_Patrocinador.getText());

                    equipoService.actualizarEquipo(equipo);
                    JOptionPane.showMessageDialog(null, "Equipo actualizado exitosamente");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el equipo");
                    ex.printStackTrace();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idEquipo = Integer.parseInt(textField_IDE.getText());
                    equipoService.eliminarEquipo(idEquipo);
                    JOptionPane.showMessageDialog(null, "Equipo eliminado exitosamente");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el equipo");
                    ex.printStackTrace();
                }
            }
        });

        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idEquipo = Integer.parseInt(textField_IDE.getText());
                    Equipo equipo = equipoService.buscarEquipoPorId(idEquipo);
                    if (equipo != null) {
                        textField_Nombre.setText(equipo.getNombre());
                        textField_Pais.setText(equipo.getPais());
                        textField_Ciudad.setText(equipo.getCiudad());
                        textField_Estadio.setText(equipo.getEstadio());
                        textField_Fundacion.setText(String.valueOf(equipo.getFundacion()));
                        textField_Entrenador.setText(equipo.getEntrenador());
                        textField_WO.setText(equipo.getWebOficial());
                        textField_FB.setText(equipo.getFacebook());
                        textField_X.setText(equipo.getTwitter());
                        textField_IG.setText(equipo.getInstagram());
                        textField_Patrocinador.setText(equipo.getPatrocinadorPrincipal());
                    } else {
                        JOptionPane.showMessageDialog(null, "Equipo no encontrado");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar el equipo");
                    ex.printStackTrace();
                }
            }
        });
    }
}
