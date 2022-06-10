package observer;

/**
 * Observer es una interfaz que, siguiendo el patr�n de dise�o 'Observer',
 * actualiza los elementos correspondientes en funci�n de la clase que
 * implemente dicha interfaz.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public interface Observer {

	/**
	 * Actualiza los elementos que determinen los observadores correspondientes en
	 * funci�n del emisor de la orden.
	 * 
	 * @param emisor El objeto que provoca la actualizaci�n de los observadores
	 */
	void update(Object emisor);
}
