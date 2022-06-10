package igu;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import igu.internacionalizacion.Mensajes;
import logica.Aplicacion;
import logica.util.ErrorAccesoException;

/**
 * Main ser� la clase desde la que se instanciar� el objeto principal de la
 * l�gica "Aplicacion" as� como el objeto "Mensajes" dedicado a la funcionalidad
 * de la internacionalizaci�n, y el objeto "Ventana Principal" de la IGU de la aplicaci�n.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class Main {
	/**
	 * Ejecuta la aplicaci�n e instancia la clase principal de la l�gica, la internacionalizaci�n
	 * as� como la de la IGU.
	 */
	public static void main(String[] args) throws ErrorAccesoException {
		try {
			Aplicacion app = new Aplicacion();
			Mensajes mensajes = new Mensajes();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
						VentanaPrincipal frame = new VentanaPrincipal(app, mensajes);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		} catch (ErrorAccesoException eAE) {
			JOptionPane.showMessageDialog(null, "No se han podido cargar los ficheros de datos");
		}

	}
}
