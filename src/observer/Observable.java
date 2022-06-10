package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable es una clase que, siguiendo el patr�n de dise�o 'Observer',
 * permitir� registrar y eliminar observadores sobre aquellas clases que hereden
 * de Observable, permiti�ndoles as� notificar a los observadores.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public abstract class Observable {

	private List<Observer> observers = new ArrayList<>();

	/**
	 * Registra un observador y lo a�ade a la lista de observadores.
	 * 
	 * @param o El nuevo observador de la clase
	 */
	public void registrarObservador(Observer o) {
		observers.add(o);
	}

	/**
	 * Elimina un observador espec�fico de la lista de observadores.
	 * 
	 * @param o El observador a eliminar
	 */
	public void quitarObservador(Observer o) {
		observers.remove(o);
	}

	/**
	 * Notifica a los observadores de la clase registrados disparando la orden de
	 * actualizaci�n sobre estos (recorriendo la lista de los observadores).
	 */
	protected void notificarObservadores() {
		for (Observer o : observers) {
			o.update(this);
		}
	}
}
