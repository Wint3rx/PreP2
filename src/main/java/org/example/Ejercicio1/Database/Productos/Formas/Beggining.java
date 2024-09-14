package org.example.Ejercicio1.Database.Productos.Formas;

import org.example.Ejercicio1.Database.Productos.FRM_Example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Beggining extends JFrame {
    private JPanel rootPanel;
    private JLabel lbl_F1;
    private JLabel lbl_F2;
    private JLabel lbl_F3;
    private JButton ingresarAlFormulario1Button;
    private JButton ingresarAlFormulario2Button;
    private JButton ingresarAlFormulario3Button;

    public Beggining() {
        ingresarAlFormulario1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FRM_Example form1 = new FRM_Example();
                form1.setVisible(true);
                setVisible(false);
            }
        });
        ingresarAlFormulario2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FRM_Usuarios from2 = new FRM_Usuarios();
                from2.setVisible(true);
                setVisible(false);
            }
        });
        ingresarAlFormulario3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FRM_Catalogo form3 = new FRM_Catalogo();
                form3.setVisible(true);
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Beggining");
        frame.setContentPane(new Beggining().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
