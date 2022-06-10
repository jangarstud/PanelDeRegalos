package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable es una clase que, siguiendo el patrón de diseño 'Observer',
 * permitirá registrar y eliminar observadores sobre aquellas clases que hereden
 * de Observable, permitiéndoles así notificar a los observadores.
 * 
 * @author José Antonio García Fuentes
 *
 */
public abstract class Observable {

	private List<Observer> observers = new ArrayList<>();

	/**
	 * Registra un observador y lo añade a la lista de observadores.
	 * 
	 * @param o El nuevo observador de la clase
	 */
	public void registrarObservador(Observer o) {
		observers.add(o);
	}

	/**
	 * Elimina un observador específico de la lista de observadores.
	 * 
	 * @param o El observador a eliminar
	 */
	public void quitarObservador(Observer o) {
		observers.remove(o);
	}

	/**
	 * Notifica a los observadores de la clase registrados disparando la orden de
	 * actualización sobre estos (recorriendo la lista de los observadores).
	 */
	protected void notificarObservadores() {
		for (Observer o : observers) {
			o.update(this);
		}
	}
}
