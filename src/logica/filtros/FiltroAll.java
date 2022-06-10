package logica.filtros;

import java.util.function.Predicate;

import logica.Regalo;

/**
 * FiltroAll es una clase que implementa una interfaz funcional Predicate
 * parametrizada con objetos de tipo Regalo y filtra para todos los regalos.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class FiltroAll implements Predicate<Regalo> {

	/**
	 * Define la condici�n de filtrado para la lista a filtrar (en este caso la
	 * condici�n a evaluar es siempre 'true').
	 */
	@Override
	public boolean test(Regalo t) {
		return true;
	}

}
