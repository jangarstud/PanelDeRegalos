package logica.filtros;

import java.util.function.Predicate;

import logica.Regalo;

/**
 * FiltroPuntos es una clase que implementa una interfaz funcional Predicate
 * parametrizada con objetos de tipo Regalo y filtra regalos por puntos.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class FiltroPuntos implements Predicate<Regalo> {

	private int puntos;

	/**
	 * Constructor de la clase de predicado funcional para el filtrado de los
	 * regalos por puntos.
	 * 
	 * @param puntos Los puntos a filtrar
	 */
	public FiltroPuntos(int puntos) {
		this.puntos = puntos;
	}

	/**
	 * Getter de los puntos asociados al atributo 'puntos'.
	 * 
	 * Este m�todo devuelve el valor que tiene el entero para el atributo 'puntos'.
	 * 
	 * @return Los puntos
	 */
	public int getPuntos() {
		return puntos;
	}

	/**
	 * Define la condici�n de filtrado para la lista a filtrar (que los puntos del
	 * regalo como par�metro sean inferiores o iguales a los del predicado).
	 */
	@Override
	public boolean test(Regalo t) {
		return t.getPuntos() <= puntos;
	}

}
