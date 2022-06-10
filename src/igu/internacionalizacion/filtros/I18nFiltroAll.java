package igu.internacionalizacion.filtros;

import igu.internacionalizacion.Mensajes;
import logica.filtros.FiltroAll;

/**
 * I18nFiltroAll clase que hereda de FiltroAll y permite internacionalizar los
 * mensajes asociados al filtro para todos los regalos.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class I18nFiltroAll extends FiltroAll {

	private Mensajes mensajes;

	/**
	 * Constructor de la clase de internacionalización del filtrado de todos los
	 * regalos.
	 * 
	 * @param m Objeto de mensajes sobre el que se obtendrán los textos deseados
	 */
	public I18nFiltroAll(Mensajes m) {
		this.mensajes = m;
	}

	/**
	 * Retorna la cadena de caracteras asociada a la etiqueta 'all' del fichero de
	 * recursos empleado.
	 */
	@Override
	public String toString() {
		return mensajes.getRecursosMensajes().getString("all");
	}
}
