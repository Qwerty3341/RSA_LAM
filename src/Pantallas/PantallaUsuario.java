package Pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaUsuario extends JFrame {

    private JTextField userField;

    public PantallaUsuario() {
        inicializar();
    }

    private void inicializar() {
        setTitle("Sistema de mensajería segura con RSA");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("RSA - Sistema de Mensajería segura con RSA", SwingConstants.CENTER);
        mainPanel.add(titulo, BorderLayout.NORTH);

        JPanel userPanel = new JPanel(new BorderLayout(5, 5));
        userPanel.setBorder(BorderFactory.createTitledBorder("Usuario:"));

        userField = new JTextField();
        userField.setPreferredSize(new Dimension(200, 30));
        userPanel.add(userField, BorderLayout.CENTER);

        JButton enterButton = new JButton("Enter");
        enterButton.setPreferredSize(new Dimension(80, 30));

        // Agregar ActionListener al botón
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = userField.getText().trim();
                if (!usuario.isEmpty()) {
                    // Cerrar esta pantalla
                    dispose();

                    // Abrir pantalla de mensajes
                    SwingUtilities.invokeLater(() -> {
                        PantallaMensajes pantallaMensajes = new PantallaMensajes(usuario);
                        pantallaMensajes.setVisible(true);
                    });
                } else {
                    JOptionPane.showMessageDialog(PantallaUsuario.this,
                            "Por favor ingrese un nombre de usuario",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        userPanel.add(enterButton, BorderLayout.EAST);
        mainPanel.add(userPanel, BorderLayout.CENTER);

        this.add(mainPanel);
        this.setVisible(true);
    }
}