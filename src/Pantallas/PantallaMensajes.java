package Pantallas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PantallaMensajes extends JFrame{

    public PantallaMensajes(){
        this.inicializar();
    }

    private void inicializar(){
        setTitle("Mensajería segura usando RSA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel lblTitulo = new JLabel("Mensajería segura usando RSA");
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
        JScrollPane scrollClaves = new JScrollPane(txtClaves);
        gbc.gridy = 2;
        add(scrollClaves, gbc);

        JTextArea txtMensajes = new JTextArea(10, 25);
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

        JTextField txtMensaje = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(txtMensaje, gbc);

        JButton btnSend = new JButton("Send");
        gbc.gridy = 5;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        add(btnSend, gbc);

        setVisible(true);
    }
    
}
