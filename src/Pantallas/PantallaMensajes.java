package Pantallas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import RSA.RSA;
import Servidor.Mensajeria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

public class PantallaMensajes extends JFrame {

    private JTextArea txtMensajes;
    private JTextField txtMensaje;

    private String usuario;
    private RSA rsa;

    public PantallaMensajes(String usuario) throws NoSuchAlgorithmException {
        this.usuario = usuario;
        this.rsa = new RSA();
        Mensajeria.getInstanciaDelServidor().registrarUsuario(this);
        this.inicializar();
    }

    public void recibirMensaje(String mensajeCifrado, String remitente){
        try {
            String mensajeDescifrado = rsa.decrypt(mensajeCifrado);
            txtMensajes.append("Mensaje por: "+ remitente + "\n\t" + mensajeDescifrado);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, "ERROR:" + error);
        }
    }

    private void inicializar() {
        setTitle("Mensajería segura usando RSA - Usuario: " + usuario);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("Mensajería segura usando RSA - " + usuario);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitulo, gbc);

        JLabel lblClaves = new JLabel("Claves públicas:");
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblClaves, gbc);

        JTextArea txtClaves = new JTextArea(8, 15);
        txtClaves.setEditable(false);
        JScrollPane scrollClaves = new JScrollPane(txtClaves);
        gbc.gridy = 2;
        add(scrollClaves, gbc);

        txtMensajes = new JTextArea(10, 25);
        txtMensajes.setEditable(false);
        JScrollPane scrollMensajes = new JScrollPane(txtMensajes);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(scrollMensajes, gbc);

        gbc.gridheight = 1;
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Enviar mensaje a:"), gbc);

        txtMensaje = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(txtMensaje, gbc);

        JButton btnSend = new JButton("Send");
        gbc.gridy = 4;
        gbc.gridx = 1;
        gbc.gridwidth = 1;

        btnSend.addActionListener(e -> {
                String mensaje = txtMensaje.getText();
                if (!mensaje.isEmpty()) {
                    try {
                        
                        String mensajeCifrado = rsa.encrypt(mensaje);
                        Mensajeria.getInstanciaDelServidor().broadcastMensaje(mensajeCifrado, usuario);
                        txtMensajes.append("Tú: " + mensaje + "\n");
                        txtMensaje.setText("");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error al cifrar: " + ex.getMessage());
                    }
                }
            });

        add(btnSend, gbc);

        setVisible(true);
    }

    public String getUsuario() {
        return usuario;
    }
}