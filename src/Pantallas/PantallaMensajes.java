package Pantallas;

<<<<<<< HEAD
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import RSA.RSA;
import Servidor.Mensajeria;
=======
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import RSA.RSA;
import Servidor.Mensajeria;
import java.security.NoSuchAlgorithmException;
import javax.swing.Timer;
>>>>>>> 116a00d1ecd1d00c16a80fa141d82bbb6b1e2778

public class PantallaMensajes extends JFrame {

    private JTextArea txtMensajes;
    private JTextField txtMensaje;
<<<<<<< HEAD
=======
    private JComboBox<String> comboUsuarios; // Cambiado a atributo de clase
>>>>>>> 116a00d1ecd1d00c16a80fa141d82bbb6b1e2778
    private String usuario;
    private RSA rsa;
    private Map<String, PublicKey> clavesPublicas;

    public PantallaMensajes(String usuario) throws NoSuchAlgorithmException {
        this.usuario = usuario;
        this.rsa = new RSA();
        this.clavesPublicas = new HashMap<>();
        Mensajeria.getInstanciaDelServidor().registrarUsuario(this);
        this.inicializar();
        
        // Timer para actualizar lista cada 3 segundos
        new Timer(3000, e -> actualizarListaUsuarios()).start();
    }

<<<<<<< HEAD
=======
    public void recibirMensaje(String mensajeCifrado, String remitente) {
        try {
            String mensajeDescifrado = rsa.decrypt(mensajeCifrado);
            txtMensajes.append("Mensaje de " + remitente + ":\n\t" + mensajeDescifrado + "\n");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, "ERROR:" + error);
        }
    }

>>>>>>> 116a00d1ecd1d00c16a80fa141d82bbb6b1e2778
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
<<<<<<< HEAD
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
=======
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

        comboUsuarios = new JComboBox<>();
        actualizarListaUsuarios();
        gbc.gridy = 4;
        add(comboUsuarios, gbc);

        txtMensaje = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(txtMensaje, gbc);

        JButton btnSend = new JButton("Enviar");
        gbc.gridy = 4;
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(btnSend, gbc);

        btnSend.addActionListener(e -> {
            String mensaje = txtMensaje.getText();
            if (!mensaje.isEmpty()) {
                try {
                    String destinatario = (String) comboUsuarios.getSelectedItem();
                    String mensajeCifrado = rsa.encrypt(mensaje);
                    
                    if ("Todos".equals(destinatario)) {
                        Mensajeria.getInstanciaDelServidor().broadcastMensaje(mensajeCifrado, usuario);
                    } else {
                        // Envío privado
                        PantallaMensajes destino = Mensajeria.getInstanciaDelServidor().getUsuario(destinatario);
                        if (destino != null) {
                            destino.recibirMensaje(mensajeCifrado, usuario);
                        }
                    }
                    
>>>>>>> 116a00d1ecd1d00c16a80fa141d82bbb6b1e2778
                    txtMensajes.append("Tú: " + mensaje + "\n");
                    txtMensaje.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al cifrar: " + ex.getMessage());
                }
            }
        });
<<<<<<< HEAD
        
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
=======

        setVisible(true);
    }

    private void actualizarListaUsuarios() {
        SwingUtilities.invokeLater(() -> {
            String seleccionActual = (String) comboUsuarios.getSelectedItem();
            comboUsuarios.removeAllItems();
            comboUsuarios.addItem("Todos");
            
            for (String usuario : Mensajeria.getInstanciaDelServidor().getNombresUsuarios()) {
                if (!usuario.equals(this.usuario)) {
                    comboUsuarios.addItem(usuario);
                }
            }
            
            // Restaurar selección previa si existe
            if (seleccionActual != null) {
                comboUsuarios.setSelectedItem(seleccionActual);
            }
        });
    }

    @Override
    public void dispose() {
        Mensajeria.getInstanciaDelServidor().eliminarUsuario(this);
        super.dispose();
>>>>>>> 116a00d1ecd1d00c16a80fa141d82bbb6b1e2778
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