import javax.swing.SwingUtilities;

import Pantallas.PantallaMensajes;
import Pantallas.PantallaUsuario;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() ->{
            new PantallaUsuario();
        });
    }
}
