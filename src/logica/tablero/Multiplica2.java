package logica.tablero;

/**
 * EspecialCasilla será una casilla del tablero que tendrá el identificador
 * "x2Bonus" como cadena y duplicará la puntuación actual del jugador.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class Multiplica2 implements Casilla {

	@Override
	public String getNombre() {
		return "x2Bonus";
	}

	@Override
	public void actualizar(TableroJuego tablero) {
		tablero.incPuntos(tablero.getPuntuacion());
		tablero.restarIntento();
	}

}
