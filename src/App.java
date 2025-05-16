import Pantallas.PantallaMensajes;
import Pantallas.PantallaUsuario;

public class App {
    public static void main(String[] args) throws Exception {
        PantallaUsuario pantallaDelUsuario = new PantallaUsuario();
        pantallaDelUsuario.setVisible(true);

        PantallaMensajes pantallaMensajes = new PantallaMensajes();
        pantallaMensajes.setVisible(true);
    }
}
