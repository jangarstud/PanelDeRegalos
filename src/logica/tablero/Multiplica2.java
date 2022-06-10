package logica.tablero;

/**
 * EspecialCasilla ser� una casilla del tablero que tendr� el identificador
 * "x2Bonus" como cadena y duplicar� la puntuaci�n actual del jugador.
 * 
 * @author Jos� Antonio Garc�a Fuentes
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
