package logica.filtros;

import java.util.function.Predicate;

import logica.Regalo;
import logica.Regalo.Categoria;

/**
 * FiltroCategoria es una clase que implementa una interfaz funcional Predicate
 * parametrizada con objetos de tipo Regalo y filtra regalos por categoría.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class FiltroCategoria implements Predicate<Regalo> {

	private Categoria categoria;

	/**
	 * Constructor de la clase de predicado funcional para el filtrado de los
	 * regalos por categoría.
	 * 
	 * @param categoria La categoría a filtrar
	 */
	public FiltroCategoria(Categoria categoria) {
		super();
		this.categoria = categoria;
	}

	/**
	 * Getter de la categoría asociada al atributo 'categoria'.
	 * 
	 * Este método devuelve el valor que tiene el objeto enumerado de tipo Categoria
	 * para el atributo 'categoria'.
	 * 
	 * @return La referencia a la categoría
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Define la condición de filtrado para la lista a filtrar (que coincida la
	 * categoría del regalo como parámetro y la del predicado).
	 */
	@Override
	public boolean test(Regalo t) {
		return t.getCategoriaRegalo() == categoria;
	}

}
