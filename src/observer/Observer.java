package observer;

/**
 * Observer es una interfaz que, siguiendo el patrón de diseño 'Observer',
 * actualiza los elementos correspondientes en función de la clase que
 * implemente dicha interfaz.
 * 
 * @author José Antonio García Fuentes
 *
 */
public interface Observer {

	/**
	 * Actualiza los elementos que determinen los observadores correspondientes en
	 * función del emisor de la orden.
	 * 
	 * @param emisor El objeto que provoca la actualización de los observadores
	 */
	void update(Object emisor);
}
