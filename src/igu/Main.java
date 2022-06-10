package igu;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import igu.internacionalizacion.Mensajes;
import logica.Aplicacion;
import logica.util.ErrorAccesoException;

/**
 * Main será la clase desde la que se instanciará el objeto principal de la
 * lógica "Aplicacion" así como el objeto "Mensajes" dedicado a la funcionalidad
 * de la internacionalización, y el objeto "Ventana Principal" de la IGU de la aplicación.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class Main {
	/**
	 * Ejecuta la aplicación e instancia la clase principal de la lógica, la internacionalización
	 * así como la de la IGU.
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
