package Servidor;

import java.awt.List;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private final Socket socketCliente;
    private final Scanner entrada;
    private final OutputStream salida;
    // private final Encriptador encriptador;
    // private final ClavesRSA claves;
    private final String nombreCliente;

    public Cliente(Socket socketCliente, List<Cliente> clientes) throws IOException {
        this.socketCliente = socketCliente;
        this.entrada = new Scanner(socketCliente.getInputStream());
        this.salida = socketCliente.getOutputStream();
        // this.encriptador = new Encriptador();
        // this.claves = encriptador.crearClaves();

        String linea = entrada.nextLine();
        this.nombreCliente = linea.substring(linea.indexOf("usuario:") + 8);
        
        String claveStr = "cpp:" + claves.getClavePublica().getFirst() + "," + 
                         claves.getClavePublica().getSecond() + "," +
                         claves.getClavePrivada().getFirst() + "," + 
                         claves.getClavePrivada().getSecond() + "\n";
        salida.write(claveStr.getBytes());
        salida.flush();
        System.out.println("Nuevo cliente conectado: " + nombreCliente);

        for (Cliente cliente : clientes) {
            String clavePublicaStr = "cp:" + nombreCliente + "=" + 
                                    claves.getClavePublica().getFirst() + "," + 
                                    claves.getClavePublica().getSecond() + "\n";
            cliente.salida.write(clavePublicaStr.getBytes());
            
            String clavesPublicas = "cp:" + cliente.nombreCliente + "=" + 
                                   cliente.claves.getClavePublica().getFirst() + "," + 
                                   cliente.claves.getClavePublica().getSecond() + "\n";
            salida.write(clavesPublicas.getBytes());
            salida.flush();
        }
    }

    public void recibirMensajes(List<Cliente> clientes) {
        try {
            while (socketCliente.isConnected()) {
                String lineaDestinatario = entrada.nextLine();
                String destinatario = lineaDestinatario.substring(lineaDestinatario.indexOf("destinatario:") + 13);
                String lineaMensaje = entrada.nextLine();
                String mensaje = lineaMensaje.substring(lineaMensaje.indexOf("mensaje:") + 8);
                
                for (Cliente cliente : clientes) {
                    if (cliente != this) {
                        if (!cliente.nombreCliente.equals(destinatario)) {
                            salida.write(("mensaje:" + mensaje + "\n").getBytes());
                            salida.flush();
                            continue;
                        }
                        String mensajeDesencriptado = encriptador.desencriptar(new BigInteger(mensaje), claves);
                        salida.write(("mensaje:" + mensajeDesencriptado + "\n").getBytes());
                        salida.flush();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrió una excepción en el canal de " + nombreCliente);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    // public ClavesRSA getClaves() {
    //     return claves;
    // }
}