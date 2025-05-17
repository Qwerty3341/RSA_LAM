package Servidor;

import java.util.ArrayList;
import java.util.List;
import Pantallas.PantallaMensajes;

public class Mensajeria {
    private static Mensajeria instanciaDelServidor;
    private List<PantallaMensajes> usuariosConectados;

    private Mensajeria(){
        this.usuariosConectados = new ArrayList<>();
    }

    public static synchronized Mensajeria getInstanciaDelServidor(){
        if (instanciaDelServidor == null) {
            instanciaDelServidor = new Mensajeria();
        }
        return instanciaDelServidor;
    }

    public void registrarUsuario(PantallaMensajes usuario){
        usuariosConectados.add(usuario);
    }

    public void broadcastMensaje(String mensajeOriginal, String remitente){
        for(PantallaMensajes usuario : usuariosConectados){
            if(!usuario.getUsuario().equals(remitente)){
                usuario.recibirMensaje(mensajeOriginal, remitente);
            }
        }
    }
}
