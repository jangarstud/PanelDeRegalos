package igu.internacionalizacion.filtros;

import igu.internacionalizacion.Mensajes;
import logica.filtros.FiltroPuntos;

/**
 * I18nFiltroPuntos clase que hereda de FiltroPuntos y permite internacionalizar
 * los mensajes asociados al filtro para los puntos de los regalos escogidos.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class I18nFiltroPuntos extends FiltroPuntos {

	private Mensajes mensajes;

	/**
	 * Constructor de la clase de internacionalizaci�n del filtrado de los regalos
	 * en funci�n de los puntos.
	 * 
	 * @param puntos Los puntos que posee el regalo
	 * @param m      Objeto de mensajes sobre el que se obtendr�n los textos
	 *               deseados
	 */
	public I18nFiltroPuntos(int puntos, Mensajes m) {
		super(puntos);
		this.mensajes = m;
	}

	/**
	 * Retorna la cadena de caracteras asociada a la etiqueta 'pts' del fichero de
	 * recursos empleado. Esta cadena estar� ya formateada con los puntos
	 * correspondientes del regalo.
	 */
	@Override
	public String toString() {
		return String.format(mensajes.getRecursosMensajes().getString("pts"), getPuntos());
	}

}
