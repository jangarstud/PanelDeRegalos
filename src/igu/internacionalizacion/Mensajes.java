package igu.internacionalizacion;

import java.util.Locale;
import java.util.ResourceBundle;

import observer.Observable;

/**
 * Mensajes representará la clase que posibilite la localización de la aplicación
 * así como su internacionalización a través de los archivos de recursos.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class Mensajes extends Observable{
	
	private ResourceBundle mensajes;
	
	/**
	 * Constructor por defecto de la clase Mensajes que se encarga de inicializar 
	 * la localización de la aplicación con la localización de la máquina por defecto.
	 */
	public Mensajes() {
		Locale localizacion = Locale.getDefault(Locale.Category.FORMAT);
		localizar(localizacion);
	}
	
	/**
	 * Localiza la aplicación a partir del fichero de recursos 'Info'.
	 * 
	 * Además, al hacer uso del patrón Observer y ser esta clase un Observable,
	 * al llamar a este método se notificará a todos los observadores para
	 * poder así actualizar todas las etiquetas y mensajes correspondientes.
	 * 
	 * @param localizacion La localización con la que se actualizará la aplicación
	 */
	public void localizar(Locale localizacion) {
		mensajes = ResourceBundle.getBundle("rcs/info", localizacion);
		notificarObservadores();
	}
	
	/**
	 * Getter del paquete de recursos asociado al atributo 'mensajes'.
	 * 
	 * Este método devuelve la referencia al valor que tiene el objeto de tipo
	 * ResourceBundle para el atributo 'mensajes'.
	 * 
	 * @return La referencia al paquete de recursos para los mensajes
	 */
	public ResourceBundle getRecursosMensajes() {
		return mensajes;
	}
}
