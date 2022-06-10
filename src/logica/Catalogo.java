package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import logica.util.ErrorAccesoException;
import logica.util.ReadDataFile;

/**
 * Catalogo contendr� todos los premios de regalos contenidos en el fichero de
 * lectura "regalos.dat" y tendr� una longitud esperada de 5 elementos separados
 * por l�nea.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class Catalogo {
	private static final int EXPECTED_LENGTH = 5;
	private List<Regalo> premiosCatalogo = new ArrayList<Regalo>();

	/**
	 * Constructor por defecto de la clase Catalogo.
	 */
	public Catalogo() {

	}

	/**
	 * Carga los regalos del fichero "regalos.dat" mientras la lectura sea
	 * satisfactoria.
	 * 
	 * @throws ErrorAccesoException Excepci�n personalizada a lanzar en caso de
	 *                              fallo en las operaciones de IO
	 */
	public void cargar() throws ErrorAccesoException {
		ReadDataFile f = new ReadDataFile("files/regalos.dat");

		String[] datos;
		do {
			datos = f.read(EXPECTED_LENGTH);
			if (datos != null) {
				premiosCatalogo.add(new Regalo(datos));
			}
		} while (datos != null);

	}

	/**
	 * Devuelve una lista de regalos filtrados empleando predicados funcionales que
	 * se han definido para aplicar distintos filtros.
	 * 
	 * @param filtro El predicado funcional que se desea utilizar
	 * @return La lista de regalos filtrada
	 */
	public List<Regalo> verFiltrados(Predicate<Regalo> filtro) {
		if (filtro == null)
			return premiosCatalogo;
		return premiosCatalogo.stream().filter(filtro).collect(Collectors.toList());
	}

	/**
	 * Getter de los premios del cat�logo.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto de los premios del cat�logo
	 * para el atributo 'premiosCatalogo'.
	 * 
	 * @return Los premios del cat�logo
	 */
	public List<Regalo> getPremiosCatalogo() {
		return premiosCatalogo;
	}

}
