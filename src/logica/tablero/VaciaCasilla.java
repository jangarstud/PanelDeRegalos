package logica.tablero;

/**
 * VaciaCasilla será una casilla del tablero que tendrá el identificador "empty"
 * como cadena y gastará un turno del jugador sin realizar ninguna otra acción.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class VaciaCasilla implements Casilla {

	@Override
	public String getNombre() {
		return "empty";
	}

	@Override
	public void actualizar(TableroJuego tablero) {
		tablero.restarIntento();
	}

}
