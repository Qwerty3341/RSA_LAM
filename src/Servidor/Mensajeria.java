package Servidor;

import Pantallas.PantallaMensajes;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Mensajeria {
    private static Mensajeria instanciaDelServidor;
    private Map<String, PantallaMensajes> usuariosConectados;

    private Mensajeria() {
        usuariosConectados = new HashMap<>();
    }

    public static synchronized Mensajeria getInstanciaDelServidor() {
        if (instanciaDelServidor == null) {
            instanciaDelServidor = new Mensajeria();
        }
        return instanciaDelServidor;
    }

    public void registrarUsuario(PantallaMensajes usuario) {
        usuariosConectados.put(usuario.getUsuario(), usuario);
    }

    public void eliminarUsuario(PantallaMensajes usuario) {
        usuariosConectados.remove(usuario.getUsuario());
    }

    public List<String> getNombresUsuarios() {
        return new ArrayList<>(usuariosConectados.keySet());
    }

    public PantallaMensajes getUsuario(String nombre) {
        return usuariosConectados.get(nombre);
    }

    public void broadcastMensaje(String mensajeCifrado, String remitente) {
        for (PantallaMensajes usuario : usuariosConectados.values()) {
            if (!usuario.getUsuario().equals(remitente)) {
                usuario.recibirMensaje(mensajeCifrado, remitente);
            }
        }
    }
}