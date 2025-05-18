package Pantallas;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import RSA.RSA;
import Servidor.Mensajeria;

public class PantallaMensajes extends JFrame {

    private JTextArea txtMensajes;
    private JTextField txtMensaje;
    private String usuario;
    private RSA rsa;
    private Map<String, PublicKey> clavesPublicas;

    public PantallaMensajes(String usuario) throws NoSuchAlgorithmException {
        this.usuario = usuario;
        this.rsa = new RSA();
        this.clavesPublicas = new HashMap<>();
        Mensajeria.getInstanciaDelServidor().registrarUsuario(this);
        this.inicializar();
    }

    private void inicializar() {
        setTitle("Mensajería segura usando RSA - Usuario: " + usuario);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel superior
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createTitledBorder("Otros usuarios del sistema"));
        topPanel.add(new JLabel("Usuario: " + usuario));
        add(topPanel, BorderLayout.NORTH);

        // Panel central dividido
        JSplitPane centerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        // Panel de claves públicas
        JPanel clavesPanel = new JPanel(new BorderLayout());
        clavesPanel.setBorder(BorderFactory.createTitledBorder("Claves públicas"));
        
        JTextArea clavesArea = new JTextArea();
        clavesArea.setEditable(false);
        clavesArea.setText("Clave pública de " + usuario + ":\n" + rsa.getLlavePublica().toString());
        JScrollPane clavesScroll = new JScrollPane(clavesArea);
        clavesPanel.add(clavesScroll, BorderLayout.CENTER);
        
        // Panel de mensajes
        JPanel mensajesPanel = new JPanel(new BorderLayout());
        mensajesPanel.setBorder(BorderFactory.createTitledBorder("Área de mensajes"));
        
        txtMensajes = new JTextArea();
        txtMensajes.setEditable(false);
        JScrollPane mensajesScroll = new JScrollPane(txtMensajes);
        mensajesPanel.add(mensajesScroll, BorderLayout.CENTER);
        
        // Panel de envío
        JPanel enviarPanel = new JPanel(new BorderLayout());
        txtMensaje = new JTextField();
        txtMensaje.setDocument(new JTextFieldLimit(40)); // Limita a 40 caracteres
        
        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(e -> {
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
        
        enviarPanel.add(new JLabel("Mensaje nuevo (max 40 caracteres):"), BorderLayout.NORTH);
        enviarPanel.add(txtMensaje, BorderLayout.CENTER);
        enviarPanel.add(btnEnviar, BorderLayout.EAST);
        
        mensajesPanel.add(enviarPanel, BorderLayout.SOUTH);
        
        centerPanel.setLeftComponent(clavesPanel);
        centerPanel.setRightComponent(mensajesPanel);
        centerPanel.setDividerLocation(250);
        
        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void recibirMensaje(String mensajeCifrado, String remitente) {
        try {
            String mensajeDescifrado = rsa.decrypt(mensajeCifrado);
            txtMensajes.append(remitente + ": " + mensajeDescifrado + "\n");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, "ERROR al descifrar: " + error);
        }
    }

    public String getUsuario() {
        return usuario;
    }

    // Clase para limitar caracteres en el JTextField
    private static class JTextFieldLimit extends PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
}