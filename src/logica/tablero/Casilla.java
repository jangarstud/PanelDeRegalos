package logica.tablero;

/**
 * Casilla será una interfaz del tablero que tendrá una cadena como nombre
 * asociado al igual que la operación de actualizar el tablero.
 * 
 * @author José Antonio García Fuentes
 *
 */
public interface Casilla {

	/**
	 * Getter del nombre de la casilla.
	 * 
	 * Este método devuelve el valor que tiene el nombre de la casilla en función de
	 * ésta, de tal modo que se pueda acceder a su imagen asociada.
	 * 
	 * @return El nombre de la casilla
	 */
	String getNombre();

	/**
	 * Actualiza el estado del tablero en función de la casilla "destapada" o
	 * accionada
	 * 
	 * @param tablero El tablero que se va a alterar
	 */
	void actualizar(TableroJuego tablero);
}
