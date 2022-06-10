package logica.tablero;

/**
 * PuntuacionCasilla será una casilla del tablero que tendrá el identificador
 * "Xpts", siendo X el número de puntos establecido al crear la casilla, e
 * incrementará la puntuación en los mismos puntos que posea la clase.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class PuntuacionCasilla implements Casilla {
	int puntuacion;

	/**
	 * Constructor de la clase PuntuacionCasilla que tendrá los mismos puntos que se
	 * pasen como parámetro.
	 * 
	 * 
	 * @param puntuacion La puntuación de la casilla a incrementar
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
