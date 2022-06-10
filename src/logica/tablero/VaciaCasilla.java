package logica.tablero;

/**
 * VaciaCasilla ser� una casilla del tablero que tendr� el identificador "empty"
 * como cadena y gastar� un turno del jugador sin realizar ninguna otra acci�n.
 * 
 * @author Jos� Antonio Garc�a Fuentes
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
