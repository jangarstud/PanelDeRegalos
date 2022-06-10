package logica.filtros;

import java.util.function.Predicate;

import logica.Regalo;
import logica.Regalo.Categoria;

/**
 * FiltroCategoria es una clase que implementa una interfaz funcional Predicate
 * parametrizada con objetos de tipo Regalo y filtra regalos por categor�a.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class FiltroCategoria implements Predicate<Regalo> {

	private Categoria categoria;

	/**
	 * Constructor de la clase de predicado funcional para el filtrado de los
	 * regalos por categor�a.
	 * 
	 * @param categoria La categor�a a filtrar
	 */
	public FiltroCategoria(Categoria categoria) {
		super();
		this.categoria = categoria;
	}

	/**
	 * Getter de la categor�a asociada al atributo 'categoria'.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto enumerado de tipo Categoria
	 * para el atributo 'categoria'.
	 * 
	 * @return La referencia a la categor�a
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Define la condici�n de filtrado para la lista a filtrar (que coincida la
	 * categor�a del regalo como par�metro y la del predicado).
	 */
	@Override
	public boolean test(Regalo t) {
		return t.getCategoriaRegalo() == categoria;
	}

}
