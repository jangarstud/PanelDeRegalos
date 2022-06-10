package logica.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * ReadDataFile será una clase encargada concretamente de la lectura de
 * ficheros.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class ReadDataFile {

	private BufferedReader fichero;

	/**
	 * Constructor de la clase ReadDataFile que recibe un fichero para su lectura
	 * como parámetro. En caso de que no se encuentre se lanzará una excepción
	 * personalizada ErrorAccesoException.
	 * 
	 * @param file Archivo a cargar para su lectura
	 * @throws ErrorAccesoException Excepción a lanzar en caso de fallos en la
	 *                              operación de carga
	 */
	public ReadDataFile(String file) throws ErrorAccesoException {
		try {
			fichero = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ErrorAccesoException();
		}
	}

	/**
	 * Lee el fichero cargado teniendo en cuenta la longitud esperada. En caso de no
	 * cumplirse la longitud se lanza también una excepción personalizada
	 * ErrorAccesoException. Cada propiedad estará separada por "@"'s.
	 * 
	 * @param expectedLength La longitud de atributos esperada
	 * @return El vector de cadenas por cada atributo
	 * @throws ErrorAccesoException Excepción a lanzar en caso de no cumplirse la
	 *                              longitud esperada o en caso de fallo en
	 *                              operaciones IO.
	 */
	public String[] read(int expectedLength) throws ErrorAccesoException {
		String linea;
		try {
			if (fichero.ready()) {
				linea = fichero.readLine();
				String[] cadena = linea.split("@");
				if (cadena.length == expectedLength)
					return cadena;
				throw new ErrorAccesoException();
			} else {
				fichero.close();
				return null;
			}
		} catch (IOException e) {
			try {
				fichero.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new ErrorAccesoException();
		}
	}

}
