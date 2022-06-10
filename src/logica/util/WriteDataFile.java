package logica.util;

import java.io.FileWriter;
import java.io.IOException;

/**
 * WriteDataFile ser� una clase encargada concretamente de la escritura de
 * ficheros.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class WriteDataFile {

	private FileWriter fichero;
	private boolean primeraLinea = true;
	private static boolean PRIMERA_ESCRITURA_ENTREGAS_SUPERADA = false;

	/**
	 * Constructor de la clase WriteDataFile que establece el fichero sobre el que
	 * escribir pasado como par�metro.
	 * 
	 * @param file El fichero sobre el que escribir
	 * @throws ErrorAccesoException Excepci�n a lanzar en caso de fallos en la
	 *                              operaci�n de IO
	 */
	public WriteDataFile(String file) throws ErrorAccesoException {
		try {
			fichero = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ErrorAccesoException();
		}
	}

	/**
	 * Constructor de la clase WriteDataFile que establece el fichero sobre el que
	 * escribir pasado como par�metro indicando si se adjunta cadena de texto a un
	 * fichero previamente escrito.
	 * 
	 * @param file               El fichero sobre el que escribir
	 * @param appendStringToFile Opci�n de adjuntar cadena de texto a un fichero
	 *                           previamente escrito
	 * @throws ErrorAccesoException Excepci�n a lanzar en caso de fallos en la
	 *                              operaci�n de IO
	 */
	public WriteDataFile(String file, boolean appendStringToFile) throws ErrorAccesoException {
		try {
			fichero = new FileWriter(file, appendStringToFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ErrorAccesoException();
		}
	}

	/**
	 * Escribe sobre el fichero el conjunto de atributos pasado como vector de
	 * cadenas por par�metro. Cada propiedad vendr� diferenciada de la siguiente por
	 * el separador "@".
	 * 
	 * @param linea Vector de cadenas que incluye las propiedades del objeto a
	 *              serializar
	 * @throws ErrorAccesoException Excepci�n a lanzar en caso de fallos en la
	 *                              operaci�n de IO
	 */
	public void write(String[] linea) throws ErrorAccesoException {
		try {

			if (!primeraLinea)
				fichero.write("\n");
			primeraLinea = false;

			boolean primerCampo = true;
			for (String valor : linea) {
				if (!primerCampo)
					fichero.write("@");
				else
					primerCampo = false;
				fichero.write(valor);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ErrorAccesoException();
		}
	}

	/**
	 * Escribe entregas sobre el fichero con las propiedades que se pasan como
	 * par�metro en forma de un vector de cadenas de texto. En caso de ser la
	 * primera escritura, en la primera l�nea a adjuntar, se a�adir� un salto de
	 * l�nea. El separador a introducir entre una propiedad y otra a serializar ser�
	 * "@".
	 * 
	 * @param linea Vector de cadenas que incluye las propiedades del objeto a
	 *              serializar
	 * @throws ErrorAccesoException Excepci�n a lanzar en caso de fallos en la
	 *                              operaci�n de IO
	 */
	// V�lido teniendo el archivo entregas.dat vac�o
	public void writeEntregas(String[] linea) throws ErrorAccesoException {
		try {

			if (PRIMERA_ESCRITURA_ENTREGAS_SUPERADA) {
				fichero.write("\n");
			} else {
				PRIMERA_ESCRITURA_ENTREGAS_SUPERADA = true;
			}

			boolean primerCampo = true;
			for (String valor : linea) {
				if (!primerCampo)
					fichero.write("@");
				else
					primerCampo = false;
				fichero.write(valor);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ErrorAccesoException();
		}
	}

	/**
	 * Cierra el flujo de escritura.
	 */
	public void cerrar() {
		try {
			fichero.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
