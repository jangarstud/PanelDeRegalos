package igu.internacionalizacion;

import java.util.Locale;
import java.util.ResourceBundle;

import observer.Observable;

/**
 * Mensajes representar� la clase que posibilite la localizaci�n de la aplicaci�n
 * as� como su internacionalizaci�n a trav�s de los archivos de recursos.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class Mensajes extends Observable{
	
	private ResourceBundle mensajes;
	
	/**
	 * Constructor por defecto de la clase Mensajes que se encarga de inicializar 
	 * la localizaci�n de la aplicaci�n con la localizaci�n de la m�quina por defecto.
	 */
	public Mensajes() {
		Locale localizacion = Locale.getDefault(Locale.Category.FORMAT);
		localizar(localizacion);
	}
	
	/**
	 * Localiza la aplicaci�n a partir del fichero de recursos 'Info'.
	 * 
	 * Adem�s, al hacer uso del patr�n Observer y ser esta clase un Observable,
	 * al llamar a este m�todo se notificar� a todos los observadores para
	 * poder as� actualizar todas las etiquetas y mensajes correspondientes.
	 * 
	 * @param localizacion La localizaci�n con la que se actualizar� la aplicaci�n
	 */
	public void localizar(Locale localizacion) {
		mensajes = ResourceBundle.getBundle("rcs/info", localizacion);
		notificarObservadores();
	}
	
	/**
	 * Getter del paquete de recursos asociado al atributo 'mensajes'.
	 * 
	 * Este m�todo devuelve la referencia al valor que tiene el objeto de tipo
	 * ResourceBundle para el atributo 'mensajes'.
	 * 
	 * @return La referencia al paquete de recursos para los mensajes
	 */
	public ResourceBundle getRecursosMensajes() {
		return mensajes;
	}
}
