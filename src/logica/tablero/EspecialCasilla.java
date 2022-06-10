package logica.tablero;

/**
 * EspecialCasilla será una casilla del tablero que tendrá el identificador
 * "extraFlip" como cadena y permitirá una tirada extra al jugador.
 * 
 * @author José Antonio García Fuentes
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
