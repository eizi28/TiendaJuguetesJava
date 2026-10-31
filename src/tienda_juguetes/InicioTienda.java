
package tienda_juguetes;

import javax.swing.SwingUtilities;
import tienda_juguetes.vista.VistaGeneral;
import tienda_juguetes.controlador.ControladorGeneral;

public class InicioTienda {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            VistaGeneral vista = new VistaGeneral();

            new ControladorGeneral(vista);

            vista.setVisible(true);
        });
    }
}
