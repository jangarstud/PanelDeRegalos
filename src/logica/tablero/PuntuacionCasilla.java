package logica.tablero;

/**
 * PuntuacionCasilla ser� una casilla del tablero que tendr� el identificador
 * "Xpts", siendo X el n�mero de puntos establecido al crear la casilla, e
 * incrementar� la puntuaci�n en los mismos puntos que posea la clase.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class PuntuacionCasilla implements Casilla {
	int puntuacion;

	/**
	 * Constructor de la clase PuntuacionCasilla que tendr� los mismos puntos que se
	 * pasen como par�metro.
	 * 
	 * 
	 * @param puntuacion La puntuaci�n de la casilla a incrementar
	 */
	public PuntuacionCasilla(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	@Override
	public String getNombre() {
		return puntuacion + "pts";
	}

	@Override
	public void actualizar(TableroJuego tablero) {
		tablero.incPuntos(puntuacion);
		tablero.restarIntento();
	}
}
