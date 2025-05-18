package Pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

public class PantallaUsuario extends JFrame {

    private JTextField userField;

    public PantallaUsuario() {
        inicializar();
    }

    private void inicializar() {
        setTitle("RSA - Sistema de Mensajería Segura con RSA");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titulo = new JLabel("Sistema de Mensajería Segura con RSA", SwingConstants.CENTER);
        mainPanel.add(titulo, BorderLayout.NORTH);

        // Panel de entrada de usuario
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Usuario:"));
        
        userField = new JTextField(15);
        inputPanel.add(userField);
        
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(e -> {
            String usuario = userField.getText().trim();
            if (!usuario.isEmpty()) {
                dispose();
                try {
                    new PantallaMensajes(usuario).setVisible(true);
                } catch (NoSuchAlgorithmException ex) {
                    JOptionPane.showMessageDialog(null, "Error al inicializar RSA: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Por favor ingrese un nombre de usuario", 
                    "Error", 
                    JOptionPane.WARNING_MESSAGE);
            }
        });
        inputPanel.add(enterButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }
}