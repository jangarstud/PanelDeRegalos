package igu.escaladoimg;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * ImagenFactoria es una clase que construye objetos en función de la casilla a
 * la que se esté accediendo. En esta clase se está empleando el patrón de
 * diseño Factory.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class ImagenFactoria {

	private static Map<String, Image> cache = new HashMap<>();
	
	/**
	 * Factory Method que retorna un tipo de imagen en función del identificador,
	 * el componente proporcionado y si se desea adaptar a éste o no. En caso de
	 * no ajustarse al componente, se hará para el ancho y alto proporcionados.
	 * 
	 * @param identificador El identificador de la imagen a crear
	 * @param componente El componente sobre el que se desea adaptar la imagen
	 * @param adaptarAlComponente Si se desea adaptar al componente o no
	 * @param ancho El ancho que se le quiere dar a la imagen
	 * @param alto El alto que se le quiere dar a la imagen
	 * @return La imagen deseada como objeto ImageIcon
	 */
	public static ImageIcon getImagen(String identificador, JComponent componente, boolean adaptarAlComponente,
			int ancho, int alto) {
		return getImagenEscalada(componente, "/img/" + identificador + ".png", adaptarAlComponente, ancho, alto);
	}

	/**
	 * Carga la imagen proveniente del fichero con la ruta pasada como cadena.
	 * 
	 * 
	 * @param fichero La ruta del fichero para la imagen de la casilla
	 * @return El objeto ImageIcon con la imagen de la casilla correspondiente
	 *         cargada
	 */
	
	/**
	 * Carga la imagen proveniente del fichero con la ruta pasada como cadena.
	 * Para facilitar un acceso más rápido a imágenes que se hayan usado antes
	 * se hace uso de una caché que asocia la ruta junto con el objeto Image.
	 * 
	 * En caso de querer adaptar dicha imagen al componente se hace el escalado
	 * correspondiente, y en caso contrario, se hace uso del ancho y alto que 
	 * se ha especificado previamente por parámetro.
	 * 
	 * 
	 * @param componente El componente para el que se desea ajustar la imagen
	 * @param rutaImagen La ruta de la imagen que se desea emplear
	 * @param adaptarAlComponente Si se desea adaptar la imagen al componente o no
	 * @param ancho El ancho que se se le quiere dar a la imagen
	 * @param alto El alto que se le quiere dar a la imagen
	 * @return El objeto ImageIcon de la imagen deseada
	 */
	private static ImageIcon getImagenEscalada(JComponent componente, String rutaImagen, boolean adaptarAlComponente,
			int ancho, int alto) {
		Image imgOriginal;
		if (cache.containsKey(rutaImagen))
			imgOriginal = cache.get(rutaImagen);
		else {
			imgOriginal = new ImageIcon(ImagenFactoria.class.getResource(rutaImagen)).getImage();
			cache.put(rutaImagen, imgOriginal);
		}

		Image imgEscalada;
		if (adaptarAlComponente) {
			imgEscalada = imgOriginal.getScaledInstance(componente.getWidth(), componente.getHeight(),
					Image.SCALE_SMOOTH);
		} else {
			imgEscalada = imgOriginal.getScaledInstance(((int) ancho), ((int) alto), Image.SCALE_FAST);
		}
		return new ImageIcon(imgEscalada);
	}

}
