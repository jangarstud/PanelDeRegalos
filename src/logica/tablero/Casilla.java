package logica.tablero;

/**
 * Casilla ser� una interfaz del tablero que tendr� una cadena como nombre
 * asociado al igual que la operaci�n de actualizar el tablero.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public interface Casilla {

	/**
	 * Getter del nombre de la casilla.
	 * 
	 * Este m�todo devuelve el valor que tiene el nombre de la casilla en funci�n de
	 * �sta, de tal modo que se pueda acceder a su imagen asociada.
	 * 
	 * @return El nombre de la casilla
	 */
	String getNombre();

	/**
	 * Actualiza el estado del tablero en funci�n de la casilla "destapada" o
	 * accionada
	 * 
	 * @param tablero El tablero que se va a alterar
	 */
	void actualizar(TableroJuego tablero);
}
