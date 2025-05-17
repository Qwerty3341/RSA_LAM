package Servidor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import RSA.RSA;

public class ConexionCliente {
    private final Socket socketCliente;
    private final String nombreCliente;
    // private final  claves;
    private final Scanner entrada;
    private final OutputStream salida;
    private final RSA encriptador;

    // public ConexionCliente(Socket socketCliente, String nombreCliente) throws IOException {
    //     this.socketCliente = socketCliente;
    //     this.nombreCliente = nombreCliente;
    //     this.entrada = new Scanner(socketCliente.getInputStream());
    //     this.salida = socketCliente.getOutputStream();
    //     // this.encriptador = new Encriptador();

    //     System.out.println("Enviando datos de usuario");
    //     salida.write(("usuario:" + nombreCliente + "\n").getBytes());
    //     salida.flush();

    //     System.out.println("Esperando claves");
    //     String linea = entrada.nextLine();
    //     String[] partes = linea.substring(linea.indexOf("cpp:") + 4).split(",");
    //     // this.claves = new ClavesRSA(
    //     //         new Pair<>(new BigInteger(partes[0]), new BigInteger(partes[1])),
    //     //         new Pair<>(new BigInteger(partes[2]), new BigInteger(partes[3])));
    //     System.out.println("Cliente conectado: " + nombreCliente);
    // }

    // public void enviarMensaje(String destinatario, String mensaje, ClavesRSA clavesDestinatario) {
    //     Executors.newSingleThreadExecutor().submit(() -> {
    //         try {
    //             salida.write(("destinatario:" + destinatario + "\n").getBytes());
    //             salida.flush();
    //             BigInteger mensajeEncriptado = encriptador.encriptar(mensaje, clavesDestinatario);
    //             salida.write(("mensaje:" + mensajeEncriptado + "\n").getBytes());
    //             salida.flush();
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //     });
    // }

    public String getNombreCliente() {
        return nombreCliente;
    }

    // public ClavesRSA getClaves() {
    // return claves;
    // }
}