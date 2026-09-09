package centrorescateanimal;

import interfaz.VentanaLogin;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            VentanaLogin login =
                    new VentanaLogin();

            login.setVisible(true);
        });
    }
}