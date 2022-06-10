package igu.internacionalizacion.filtros;

import igu.internacionalizacion.Mensajes;
import logica.Regalo.Categoria;
import logica.filtros.FiltroCategoria;

/**
 * I18nFiltroCategoria clase que hereda de FiltroCategoria y permite
 * internacionalizar los mensajes asociados al filtro para la categor�a de
 * regalos escogida.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class I18nFiltroCategoria extends FiltroCategoria {

	private Mensajes mensajes;

	/**
	 * Constructor de la clase de internacionalizaci�n del filtrado de los regalos
	 * por categor�a.
	 * 
	 * @param categoria La categor�a sobre la que se desea obtener el filtrado
	 * @param m         Objeto de mensajes sobre el que se obtendr�n los textos
	 *                  deseados
	 */
	public I18nFiltroCategoria(Categoria categoria, Mensajes m) {
		super(categoria);
		this.mensajes = m;
	}

	/**
	 * Retorna la cadena de caracteras asociada a la etiqueta cuya inicial del
	 * nombre comienza por la primera letra del objeto de tipo Categoria que se est�
	 * utilizando.
	 */
	@Override
	public String toString() {
		return mensajes.getRecursosMensajes().getString(getCategoria().name().toLowerCase().substring(1));
	}

}
