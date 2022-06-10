package logica.tablero;

/**
 * EspecialCasilla ser� una casilla del tablero que tendr� el identificador
 * "extraFlip" como cadena y permitir� una tirada extra al jugador.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class EspecialCasilla implements Casilla {

	@Override
	public String getNombre() {
		return "extraFlip";
	}

	@Override
	public void actualizar(TableroJuego tablero) {
	}

}
